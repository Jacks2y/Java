package com.jackson.clientapi.controller;

import com.jackson.clientapi.model.Client;
import com.jackson.clientapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.jackson.clientapi.dto.ClientCreateDTO;
import com.jackson.clientapi.dto.ClientUpdateDTO;
import com.jackson.clientapi.dto.ClientResponseDTO;



import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> criar(@Valid @RequestBody ClientCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createClient(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClientUpdateDTO dto) {
        return ResponseEntity.ok(service.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
