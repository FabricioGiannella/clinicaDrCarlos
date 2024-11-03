package com.clinica.med.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.clinica.med.data.Paciente;
import com.clinica.med.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<List> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listar();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Paciente> pacienteId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.getPacienteId(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody Paciente p) {
        var novoPaciente = pacienteService.criar(p);
        return new ResponseEntity<>(novoPaciente, HttpStatus.OK);
    }


    
}
