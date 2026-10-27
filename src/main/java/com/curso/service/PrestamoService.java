package com.curso.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.curso.model.Prestamo;
import com.curso.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {


    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public boolean comprobarEstadoReserva(String estado){
        return estado.equals("APROBADA");
    }

    public Prestamo crear(Prestamo prestamo){
        if(!comprobarEstadoReserva(prestamo.getReserva().getEstado())){
            throw new IllegalArgumentException("Estado reserva no valido.");
        }
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> findById(Long id) {
        return prestamoRepository.findById(id);
    }

    public List<Prestamo> findByReserva_Usuario_Id(Long id) {
        return prestamoRepository.findByReserva_Usuario_Id(id);
    }

    public boolean existsById(Long id) {
        return prestamoRepository.existsById(id);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}
