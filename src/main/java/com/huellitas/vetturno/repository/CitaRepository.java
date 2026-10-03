package com.huellitas.vetturno.repository;

import com.huellitas.vetturno.model.Cita;
import com.huellitas.vetturno.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CitaRepository extends JpaRepository<Cita, Long> {
        boolean existsByVeterinarioAndFechaHora(Veterinario veterinario, LocalDateTime fechaHora);
}