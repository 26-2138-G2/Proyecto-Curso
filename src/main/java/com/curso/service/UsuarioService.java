package com.curso.service;

import com.curso.model.Usuario;
import com.curso.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario){
        if(!emailValido(usuario.getCorreo())){
            throw new IllegalArgumentException("El correo no es valido.");
        }
        return usuarioRepository.save(usuario);
    }

    public boolean emailValido(String correo){
        return correo != null
                && !correo.isBlank()
                && !correo.contains(" ")
                && correo.contains("@")
                && correo.substring(correo.indexOf("@")).contains(".");
    }

//  Metodos del repository
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
