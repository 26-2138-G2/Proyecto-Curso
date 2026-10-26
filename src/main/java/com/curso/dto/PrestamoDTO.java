package com.curso.dto;

import com.curso.model.Prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrestamoDTO {

    private Long id;
    private LocalDate fechaEntrega;
    private LocalDate fechaDevolucion;

    public PrestamoDTO(Prestamo prestamo){
        this.id = prestamo.getId();
        this.fechaEntrega = prestamo.getFechaEntrega();
        this.fechaDevolucion = prestamo.getFechaDevolucion();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}
