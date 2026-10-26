package com.curso.repository;

import com.curso.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByReserva_Usuario_Id(Long usarioId);
}
