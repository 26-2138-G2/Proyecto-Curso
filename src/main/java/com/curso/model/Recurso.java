package com.curso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recurso")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @ColumnDefault("'OPERATIVO'")
    @Column(name = "estado_operativo", nullable = false, length = 20)
    private String estadoOperativo;

    @OneToMany(mappedBy = "recurso")
    @JsonIgnore
    @NotBlank
    private List<Reserva> reservas = new ArrayList<>();

    public Recurso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo.toUpperCase();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

//  contructor

    public Recurso(String nombre, String descripcion, String estadoOperativo, List<Reserva> reservas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadoOperativo = estadoOperativo;
        this.reservas = reservas;
    }

    public Recurso(String nombre, String estadoOperativo, List<Reserva> reservas) {
        this.nombre = nombre;
        this.estadoOperativo = estadoOperativo;
        this.reservas = reservas;
    }

//  toString

    @Override
    public String toString() {
        return "Recurso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoOperativo='" + estadoOperativo + '\'' +
                '}';
    }
}