package com.huellitas.vetturno.service;

import com.huellitas.vetturno.dto.CitaDTO;
import com.huellitas.vetturno.dto.CitaRequest;
import com.huellitas.vetturno.model.Cita;
import com.huellitas.vetturno.model.Mascota;
import com.huellitas.vetturno.model.Veterinario;
import com.huellitas.vetturno.repository.CitaRepository;
import com.huellitas.vetturno.repository.MascotaRepository;
import com.huellitas.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public CitaDTO agendar(CitaRequest request) {
        // 1. Validar que la mascota exista
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        // 2. Validar que el veterinario exista
        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        // 3. Validar cruce de horario para el veterinario
        boolean existeCruce = citaRepository.existsByVeterinarioAndFechaHora(veterinario, request.getFechaHora());
        if (existeCruce) {
            throw new RuntimeException("El veterinario ya tiene una cita programada en ese horario.");
        }

        // 4. Crear y guardar la cita
        Cita cita = new Cita();
        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);

        Cita guardada = citaRepository.save(cita);

        return convertirADTO(guardada);
    }

    public List<CitaDTO> listarTodas() {
        return citaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private CitaDTO convertirADTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMotivo(cita.getMotivo());
        dto.setMascota(cita.getMascota().getNombre());
        dto.setPropietario(cita.getMascota().getPropietario().getNombre());
        dto.setVeterinario(cita.getVeterinario().getNombre());
        return dto;
    }
}