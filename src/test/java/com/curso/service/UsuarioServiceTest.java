package com.curso.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(null);
    }

    @Test
    void emailValido() {
        // Correos válidos
        assertTrue(usuarioService.emailValido("ana@gmail.com"));
        assertTrue(usuarioService.emailValido("juan@hotmail.es"));

        // Correos inválidos
        assertFalse(usuarioService.emailValido("juangmail.com"));
        assertFalse(usuarioService.emailValido("ana@gmailcom"));
        assertFalse(usuarioService.emailValido("ana @gmail.com"));
        assertFalse(usuarioService.emailValido(""));
        assertFalse(usuarioService.emailValido(null));
    }
}