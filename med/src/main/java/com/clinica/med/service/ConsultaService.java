package com.clinica.med.service;
import com.clinica.med.data.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.med.data.ConsultaRepository;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    
    public Consulta getConsultaId(Integer id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public Consulta criar(Consulta c) {
        c.setId(null);
        consultaRepository.save(c);
        return c;
    }

    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

    public void deletar(Integer id) {
        Consulta c = getConsultaId(id);
        consultaRepository.deleteById(c.getId());
    }

    public Consulta atualizar(Integer id, Consulta c) {
        Consulta consulta = getConsultaId(id);
        consulta.setNome(c.getNome());
        consulta.setTel(c.getTel());
        consulta.setEmail(c.getEmail());
        consulta.setDisponivel(c.getDisponivel());
        consultaRepository.save(consulta);
        return consulta;
    }
}
