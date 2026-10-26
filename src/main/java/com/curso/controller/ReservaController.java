package com.curso.controller;

import com.curso.model.Reserva;
import com.curso.repository.ReservaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @GetMapping
    public List<Reserva> listar(){
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id){
        return reservaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id}")
    public List<Reserva>listarPorUsuario(@PathVariable Long id){
        return reservaRepository.findByUsuarioId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva crear(@RequestBody Reserva reserva){
        reserva.setId(null);
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reserva){
        return reservaRepository.findById(id)
                .map(existente->{
                    existente.setEstado(reserva.getEstado());
                    existente.setFechaFin(reserva.getFechaFin());
                    existente.setFechaInicio(reserva.getFechaInicio());
                    existente.setFechaReserva(reserva.getFechaReserva());
                    existente.setPrestamo(reserva.getPrestamo());
                    existente.setRecurso(reserva.getRecurso());
                    existente.setUsuario(reserva.getUsuario());
                    return ResponseEntity.ok(reservaRepository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!reservaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        reservaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
