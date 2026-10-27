package com.curso.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrestamoServiceTest {

    private PrestamoService prestamoService;

    @BeforeEach
    void setUp() {
        prestamoService = new PrestamoService(null);
    }

    @Test
    void comprobarEstadoReserva() {

        // La reserva está aprobada
        assertTrue(prestamoService.comprobarEstadoReserva("APROBADA"));

        // La reserva está cancelada
        assertFalse(prestamoService.comprobarEstadoReserva("CANCELADA"));

        // La reserva está rechazada
        assertFalse(prestamoService.comprobarEstadoReserva("RECHAZADA"));
    }
}