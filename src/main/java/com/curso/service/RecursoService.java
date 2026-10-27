package com.curso.service;

import com.curso.model.Recurso;
import com.curso.repository.RecursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;

    public RecursoService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    public boolean comprobarEstadoOperativo(String estado){
        return !estado.isBlank() &&
                !estado.equals(null) &&
                (estado.toUpperCase().equals("OPERATIVO") ||
                estado.toUpperCase().equals("MANTENIMIENTO") ||
                estado.toUpperCase().equals("BAJA"));
    }

    public Recurso crear(Recurso recurso){
        if (!comprobarEstadoOperativo(recurso.getEstadoOperativo())){
            throw new IllegalArgumentException("Estado operativo no valido. Checkea eso.");
        }
        return recursoRepository.save(recurso);

    }

    public List<Recurso> findAll() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> findById(Long id) {
        return recursoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return recursoRepository.existsById(id);
    }

    public void eliminar(Long id) {
        recursoRepository.deleteById(id);
    }
}
