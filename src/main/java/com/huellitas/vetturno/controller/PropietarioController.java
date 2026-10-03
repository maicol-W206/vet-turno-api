package com.huellitas.vetturno.controller;

import com.huellitas.vetturno.dto.PropietarioDTO;
import com.huellitas.vetturno.service.PropietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    private final PropietarioService service;


    public PropietarioController(PropietarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PropietarioDTO> crearPropietario(@RequestBody PropietarioDTO dto) {
        PropietarioDTO creado = service.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED); // Retorna 201
    }

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> listarPropietarios() {
        return ResponseEntity.ok(service.listarTodos()); // Retorna 200
    }
}