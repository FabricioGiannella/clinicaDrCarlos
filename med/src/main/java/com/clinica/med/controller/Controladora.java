package com.clinica.med.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.clinica.med.data.Paciente;
import com.clinica.med.data.Consulta;
import com.clinica.med.service.ConsultaService;
import com.clinica.med.service.PacienteService;
import java.util.List;

@Controller
public class Controladora {
    
    @Autowired
    PacienteService pacienteService;

    @Autowired
    ConsultaService consultaService;

    Paciente pacienteLogado;
    String mensagem = "";

    @GetMapping("/") //página index
    public String paginaIndex() {
        return "index";
    }

    @GetMapping("/tratamentos") //página tratamentos
    public String paginaTratamentos() {
        return "tratamentos";
    }

    @GetMapping("/local") //página localização
    public String paginaLocal() {
        return "local";
    }

    @GetMapping("/login") //página login
    public String paginaLogin(Model m, Model msg) {
        m.addAttribute("p", new Paciente());
        msg.addAttribute("msg", mensagem);
        return "login";
    }

    @GetMapping("/cad") //página cadastro
    public String paginaCadastro(Model m, Model msg) {
        m.addAttribute("p", new Paciente());
        msg.addAttribute("msg", mensagem);
        return "cad";
    }

    @GetMapping("/adm") //página adm
    public String paginaAdm(Model m, Model c) {
        m.addAttribute("c", new Consulta());
        c.addAttribute("cs", consultaService.listar());
        return "adm";
    }

    @GetMapping("/marcar") //página marcar
    public String paginaMarcar(Model m, Model n, Model ms) {
        ms.addAttribute("cs", consultaService.listar());
        m.addAttribute("c", new Consulta());
        n.addAttribute("p", pacienteLogado);
        return "marcar";
    }

    //--------------------------------------------------

    @PostMapping("/fazerLogin") //faz login seleciona página
    public String formFazerLogin(@ModelAttribute Paciente p) {
        List<Paciente> pacientes = pacienteService.listar();
        if(p.getEmail().isEmpty() || p.getEmail().isBlank() || p.getSenha().isEmpty() || p.getSenha().isBlank()) {
            return "redirect:/login";
        }
        for(Paciente x : pacientes) {
            if (x.getEmail().equals(p.getEmail()) && x.getSenha().equals(p.getSenha())) {
                pacienteLogado = x;
                return "redirect:/marcar";
            }
        }
        if(p.getEmail().equals("adm@adm") && p.getSenha().equals("adm")) {
            return "redirect:/adm";
        }
        mensagem = "Usuário não encontrado!";
        return "redirect:/login";
    }

    @PostMapping("/cadastrar") //cadastra novo paciente
    public String fazerCadastro(@ModelAttribute Paciente p) {
        mensagem = "";
        if((!verificaNome(p.getNome())) || (!verificaTel(p.getTel())) || (!verificaEmail(p.getEmail())) || (!verificaSenha(p.getSenha()))) {
            return "redirect:/cad";
        }
        else if(!verificaCadastro(p.getEmail())) {
            mensagem = "Email já cadastrado!";
            return "redirect:/cad";
        }
        else {
            mensagem = "";
            Paciente paciente = new Paciente();
            paciente.setNome(p.getNome());
            paciente.setEmail(p.getEmail());
            paciente.setTel(p.getTel());
            paciente.setSenha(p.getSenha());
            pacienteService.criar(paciente);
            return "redirect:/login";
        }
    }

    @PostMapping("/gerarConsulta") //gera consulta
    public String geraConsulta(@ModelAttribute Consulta c) {
        Consulta consulta = new Consulta();
        consulta.setData(c.getData());
        consulta.setNome(" ");
        consulta.setEmail(" ");
        consulta.setTel(" ");
        consulta.setDisponivel("true");
        consultaService.criar(consulta);
        return "redirect:/adm";
    }

    @PostMapping("/marcarConsulta") //marcar a consulta
    public String marcarConsulta(@ModelAttribute Consulta c) {
        Integer id = (int) c.getId();
        Consulta consulta = new Consulta();
        consulta.setNome(pacienteLogado.getNome());
        consulta.setEmail(pacienteLogado.getEmail());
        consulta.setTel(pacienteLogado.getTel());
        consulta.setDisponivel("false");
        consultaService.atualizar(id, consulta);
        return "redirect:/marcar";
    }


    //----------------------------------------------------------

    public boolean verificaNome(String nome) {
        if(nome.isBlank() || nome.isEmpty() || nome.length() < 8) {
            return false;
        }
        return true;
    }

    public boolean verificaTel(String tel) {
        if(tel.isEmpty() || tel.isBlank() || tel.length() < 8) {
            return false;
        }
        return true;
    }

    public boolean verificaEmail(String email) {
        boolean arroba = false;
        if(email.contains("@")) {
            arroba = true;
        }
        if(arroba == false || email.isBlank() || email.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean verificaSenha(String senha) {
        if(senha.isBlank() || senha.isEmpty() || senha.length() < 3) {
            return false;
        }
        return true;
    }

    public boolean verificaCadastro(String email) {
        List<Paciente> pacientes = pacienteService.listar();
        for(Paciente x : pacientes) {
            if(x.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
