package com.curso.service;


import aj.org.objectweb.asm.commons.Remapper;
import com.curso.model.Recurso;
import com.curso.model.Reserva;
import com.curso.repository.RecursoRepository;
import com.curso.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final RecursoRepository recursoRepository;
    private ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository, RecursoRepository recursoRepository) {
        this.reservaRepository = reservaRepository;
        this.recursoRepository = recursoRepository;
    }

    public boolean comprobarFechas(LocalDate fechaInicio, LocalDate fechaFin){
        return fechaInicio != null &&
                fechaFin != null &&
                fechaInicio.isBefore(fechaFin);
    }
    public boolean comprobarRecurso(Long id){
        Optional<Recurso> resultado = recursoRepository.findById(id);
        if(resultado.isPresent()) {
            Recurso recurso = resultado.get();
            if (recurso.getEstadoOperativo().equals("OPERATIVO")) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarEstado(String estado){
        return estado != null &&
               !estado.isBlank() &&
                (estado.equals("APROBADA") ||
                estado.equals("CANCELADA") ||
                estado.equals("RECHAZADA"));
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> findByUsuarioId(Long id) {
        return reservaRepository.findByUsuarioId(id);
    }

    public Reserva crear(Reserva reserva) {
        if (!comprobarFechas(reserva.getFechaInicio(), reserva.getFechaFin())){
            throw new IllegalArgumentException("Fechas no validas.Checkea eso por favor.");
        }
        else if (!comprobarRecurso(reserva.getRecurso().getId())){
            throw new IllegalArgumentException("Estado recurso no valido.");
        }
        else if (!comprobarEstado(reserva.getEstado())) {
            throw new IllegalArgumentException("Estado de la reserva no valido.");
        }
        return reservaRepository.save(reserva);
    }

    public boolean existsById(Long id) {
        return reservaRepository.existsById(id);
    }


    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}
