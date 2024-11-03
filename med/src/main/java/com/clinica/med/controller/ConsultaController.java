package com.clinica.med.controller;
import com.clinica.med.data.Consulta;
import com.clinica.med.service.ConsultaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    ConsultaService consultaService;

    @GetMapping("/listar")
    public ResponseEntity<List> listarConsultas() {
        List<Consulta> consultas = consultaService.listar();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Consulta> consultaId(@PathVariable Integer id) {
        Consulta consulta = consultaService.getConsultaId(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    } 

    @PostMapping("/adicionar")
    public ResponseEntity<Consulta> adicionarConsulta(@RequestBody Consulta c) {
        var novaConsulta = consultaService.criar(c);
        return new ResponseEntity<>(novaConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarConsulta(@PathVariable Integer id) {
        consultaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable Integer id, @RequestBody Consulta c) {
        var consultaAtualizada = consultaService.atualizar(id, c);
        return new ResponseEntity<>(consultaAtualizada, HttpStatus.OK);
    }
}
