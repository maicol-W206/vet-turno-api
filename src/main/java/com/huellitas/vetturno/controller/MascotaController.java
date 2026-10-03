package com.huellitas.vetturno.controller;

import com.huellitas.vetturno.dto.MascotaDTO;
import com.huellitas.vetturno.dto.MascotaRequest;
import com.huellitas.vetturno.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService service;

    public MascotaController(MascotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaRequest request) {
        MascotaDTO creada = service.crear(request);
        return new ResponseEntity<>(creada, HttpStatus.CREATED); // Retorna 201
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listarMascotas() {
        return ResponseEntity.ok(service.listarTodas()); // Retorna 200
    }
}