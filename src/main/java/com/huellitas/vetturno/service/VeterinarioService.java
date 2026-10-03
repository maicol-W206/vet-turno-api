package com.huellitas.vetturno.service;

import com.huellitas.vetturno.dto.VeterinarioDTO;
import com.huellitas.vetturno.model.Veterinario;
import com.huellitas.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinarioService {

    private final VeterinarioRepository repository;

    public VeterinarioService(VeterinarioRepository repository) {
        this.repository = repository;
    }

    public VeterinarioDTO crear(VeterinarioDTO dto) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(dto.getNombre());
        veterinario.setEspecie(dto.getEspecialidad()); // Mapea especialidad

        Veterinario guardado = repository.save(veterinario);

        dto.setId(guardado.getId());
        return dto;
    }

    public List<VeterinarioDTO> listarTodos() {
        return repository.findAll().stream().map(v -> {
            VeterinarioDTO dto = new VeterinarioDTO();
            dto.setId(v.getId());
            dto.setNombre(v.getNombre());
            dto.setEspecialidad(v.getEspecialidad());
            return dto;
        }).collect(Collectors.toList());
    }
}