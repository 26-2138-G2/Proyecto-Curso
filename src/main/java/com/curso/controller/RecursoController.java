package com.curso.controller;

import com.curso.model.Recurso;
import com.curso.service.RecursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    private final RecursoService recursoService;


    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping
    public List<Recurso> listar(){
        return recursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> obtener(@PathVariable Long id){
        Optional<Recurso> recurso = recursoService.findById(id);
        if(recurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(recurso.get());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recurso crear(@RequestBody Recurso recurso){
        recurso.setId(null);
        return recursoService.crear(recurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizar(@PathVariable Long id, @RequestBody Recurso recurso){
        return recursoService.findById(id)
                .map(existente->{
                    existente.setDescripcion(recurso.getDescripcion());
                    existente.setEstadoOperativo(recurso.getEstadoOperativo());
                    existente.setNombre(recurso.getNombre());
                    existente.setReservas((recurso.getReservas()));
                    return ResponseEntity.ok(recursoService.crear(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!recursoService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        recursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
