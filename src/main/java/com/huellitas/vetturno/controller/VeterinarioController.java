package com.huellitas.vetturno.controller;

import com.huellitas.vetturno.dto.VeterinarioDTO;
import com.huellitas.vetturno.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService service;

    public VeterinarioController(VeterinarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VeterinarioDTO> crearVeterinario(@RequestBody VeterinarioDTO dto) {
        VeterinarioDTO creado = service.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> listarVeterinarios() {
        return ResponseEntity.ok(service.listarTodos()); // 200 OK
    }
}