package com.curso.controller;

import com.curso.model.Reserva;
import com.curso.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listar(){
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id){
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id}")
    public List<Reserva>listarPorUsuario(@PathVariable Long id){
        return reservaService.findByUsuarioId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva crear(@RequestBody Reserva reserva){
        reserva.setId(null);
        return reservaService.crear(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reserva){
        return reservaService.findById(id)
                .map(existente->{
                    existente.setEstado(reserva.getEstado());
                    existente.setFechaFin(reserva.getFechaFin());
                    existente.setFechaInicio(reserva.getFechaInicio());
                    existente.setFechaReserva(reserva.getFechaReserva());
                    existente.setPrestamo(reserva.getPrestamo());
                    existente.setRecurso(reserva.getRecurso());
                    existente.setUsuario(reserva.getUsuario());
                    return ResponseEntity.ok(reservaService.crear(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!reservaService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
