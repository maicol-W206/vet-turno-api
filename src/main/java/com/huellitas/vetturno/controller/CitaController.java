package com.huellitas.vetturno.controller;

import com.huellitas.vetturno.dto.CitaDTO;
import com.huellitas.vetturno.dto.CitaRequest;
import com.huellitas.vetturno.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> agendarCita(@RequestBody CitaRequest request) {
        CitaDTO creada = service.agendar(request);
        return new ResponseEntity<>(creada, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        return ResponseEntity.ok(service.listarTodas()); // 200 OK
    }
}