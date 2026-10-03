package com.huellitas.vetturno.service;

import com.huellitas.vetturno.dto.MascotaDTO;
import com.huellitas.vetturno.dto.MascotaRequest;
import com.huellitas.vetturno.model.Mascota;
import com.huellitas.vetturno.model.Propietario;
import com.huellitas.vetturno.repository.MascotaRepository;
import com.huellitas.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public MascotaDTO crear(MascotaRequest request) {
        Propietario propietario = propietarioRepository.findById(request.getPropietarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));

        Mascota mascota = new Mascota();
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setPropietario(propietario);

        Mascota guardada = mascotaRepository.save(mascota);

        return convertirADTO(guardada);
    }

    public List<MascotaDTO> listarTodas() {
        return mascotaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private MascotaDTO convertirADTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setRaza(mascota.getRaza());
        if (mascota.getPropietario() != null) {
            dto.setPropietarioId(mascota.getPropietario().getId());
            dto.setPropietarioNombre(mascota.getPropietario().getNombre());
        }
        return dto;
    }
}