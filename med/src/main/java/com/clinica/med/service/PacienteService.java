package com.clinica.med.service;
import com.clinica.med.data.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.med.data.PacienteRepository;
import java.util.List;

@Service
public class PacienteService {
    
    @Autowired
    PacienteRepository pacienteRepository;

    public Paciente getPacienteId(Integer id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente criar(Paciente p) {
        p.setId(null);
        pacienteRepository.save(p);
        return p;
    }

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }
}
