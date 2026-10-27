package com.curso.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursoServiceTest {

    private RecursoService recursoService;

    @BeforeEach
    void setUp() {
        recursoService = new RecursoService(null);
    }

    @Test
    void comprobarEstadoOperativo() {
        assertTrue(recursoService.comprobarEstadoOperativo("OPERATIVO"));
        assertTrue(recursoService.comprobarEstadoOperativo("MANTENIMIENTO"));
        assertTrue(recursoService.comprobarEstadoOperativo("BAJA"));

        assertFalse(recursoService.comprobarEstadoOperativo("DISPONIBLE"));
        assertFalse(recursoService.comprobarEstadoOperativo(""));
        assertFalse(recursoService.comprobarEstadoOperativo("   "));
        assertFalse(recursoService.comprobarEstadoOperativo(null));
    }
}