package com.curso.controller;

import com.curso.dto.PrestamoDTO;
import com.curso.model.Prestamo;
import com.curso.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listar(){
        return prestamoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id){
        return prestamoService.findById(id)
                .map(prestamo -> ResponseEntity.ok(prestamo))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id}")
    public List<PrestamoDTO> listarPorUsuario(@PathVariable Long id){
        return prestamoService.findByReserva_Usuario_Id(id)
                .stream()
                .map(PrestamoDTO::new)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo crear(@RequestBody Prestamo prestamo){
        prestamo.setId(null);
        return prestamoService.crear(prestamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo){
        return prestamoService.findById(id)
                .map(existente->{
                    existente.setReserva(prestamo.getReserva());
                    existente.setFechaEntrega(prestamo.getFechaEntrega());
                    existente.setFechaDevolucion(prestamo.getFechaDevolucion());
                    return ResponseEntity.ok(prestamoService.crear(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!prestamoService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
