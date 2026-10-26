package com.curso.controller;

import com.curso.dto.PrestamoDTO;
import com.curso.model.Prestamo;
import com.curso.model.Recurso;
import com.curso.repository.PrestamoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping
    public List<Prestamo> listar(){
        return prestamoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id){
        return prestamoRepository.findById(id)
                .map(prestamo -> ResponseEntity.ok(prestamo))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id}")
    public List<PrestamoDTO> listarPorUsuario(@PathVariable Long id){
        return prestamoRepository.findByReserva_Usuario_Id(id)
                .stream()
                .map(PrestamoDTO::new)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo crear(@RequestBody Prestamo prestamo){
        prestamo.setId(null);
        return prestamoRepository.save(prestamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo){
        return prestamoRepository.findById(id)
                .map(existente->{
                    existente.setReserva(prestamo.getReserva());
                    existente.setFechaEntrega(prestamo.getFechaEntrega());
                    existente.setFechaDevolucion(prestamo.getFechaDevolucion());
                    return ResponseEntity.ok(prestamoRepository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!prestamoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        prestamoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
