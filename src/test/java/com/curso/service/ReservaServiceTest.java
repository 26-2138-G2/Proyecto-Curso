package com.curso.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {

    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        reservaService = new ReservaService(null, null);
    }
    @Test
    void comprobarFechas() {
        LocalDate inicio = LocalDate.of(2026, 11, 10);
        LocalDate fin = LocalDate.of(2026, 11, 15);

        // Fechas correctas
        assertTrue(reservaService.comprobarFechas(inicio, fin));

        // Fecha de inicio posterior a la fecha de fin
        assertFalse(reservaService.comprobarFechas(fin, inicio));

        // Fechas iguales
        assertFalse(reservaService.comprobarFechas(inicio, inicio));

        // Fecha de inicio nula
        assertFalse(reservaService.comprobarFechas(null, fin));

        // Fecha de fin nula
        assertFalse(reservaService.comprobarFechas(inicio, null));

        // Ambas fechas nulas
        assertFalse(reservaService.comprobarFechas(null, null));
    }

    @Test
    void comprobarEstados() {
        // Estados válidos
        assertTrue(reservaService.comprobarEstado("APROBADA"));
        assertTrue(reservaService.comprobarEstado("CANCELADA"));
        assertTrue(reservaService.comprobarEstado("RECHAZADA"));

        // Estado no permitido
        assertFalse(reservaService.comprobarEstado("PENDIENTE"));
        assertFalse(reservaService.comprobarEstado("INVALIDO"));

        // Estado vacío
        assertFalse(reservaService.comprobarEstado(""));

        // Estado con espacios en blanco
        assertFalse(reservaService.comprobarEstado("   "));

        // Estado nulo
        assertFalse(reservaService.comprobarEstado(null));
    }

}