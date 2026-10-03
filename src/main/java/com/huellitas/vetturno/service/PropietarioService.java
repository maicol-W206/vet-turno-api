package com.huellitas.vetturno.service;
import com.huellitas.vetturno.dto.PropietarioDTO;
import com.huellitas.vetturno.model.Propietario;
import com.huellitas.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropietarioService {

    private final PropietarioRepository repository;


    public PropietarioService(PropietarioRepository repository) {
        this.repository = repository;
    }

    public PropietarioDTO crear(PropietarioDTO dto) {

        Propietario propietario = new Propietario();
        propietario.setNombre(dto.getNombre());
        propietario.setTelefono(dto.getTelefono());
        propietario.setEmail(dto.getEmail());

        Propietario guardado = repository.save(propietario);


        dto.setId(guardado.getId());
        return dto;
    }

    public List<PropietarioDTO> listarTodos() {

        return repository.findAll().stream().map(p -> {
            PropietarioDTO dto = new PropietarioDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setTelefono(p.getTelefono());
            dto.setEmail(p.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }
}