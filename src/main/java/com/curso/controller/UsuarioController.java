package com.curso.controller;

import com.curso.model.Usuario;
import com.curso.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
//
//  Recuperar todos los usuarios
//
    @GetMapping()
    public List<Usuario> listar(){
       return usuarioService.findAll();
    }

//
//  Devuelve el usuario especificado.
//
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id){
        return usuarioService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build() );
    }

//
//  @PostMapping
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario){
        usuario.setId(null);
        return usuarioService.crearUsuario(usuario);
    }

//
//  @PutMapping
//
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.findById(id)
                .map(existente->{
                    existente.setContrasena(usuario.getContrasena());
                    existente.setCorreo(usuario.getCorreo());
                    existente.setNombre(usuario.getNombre());
                    existente.setRol(usuario.getRol());
                    return ResponseEntity.ok(usuarioService.crearUsuario(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

//
//  @DeleteMapping
//
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(!usuarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
