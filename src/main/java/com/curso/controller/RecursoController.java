package com.curso.controller;

import com.curso.model.Recurso;
import com.curso.repository.RecursoRepository;
import com.curso.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    private final RecursoRepository recursoRepository;


    public RecursoController(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @GetMapping
    public List<Recurso> listar(){
        return recursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> obtener(@PathVariable Long id){
        Optional<Recurso> recurso = recursoRepository.findById(id);
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
        return recursoRepository.save(recurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizar(@PathVariable Long id, @RequestBody Recurso recurso){
        return recursoRepository.findById(id)
                .map(existente->{
                    existente.setDescripcion(recurso.getDescripcion());
                    existente.setEstadoOperativo(recurso.getDescripcion());
                    existente.setNombre(recurso.getNombre());
                    existente.setReservas((recurso.getReservas()));
                    return ResponseEntity.ok(recursoRepository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!recursoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        recursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
