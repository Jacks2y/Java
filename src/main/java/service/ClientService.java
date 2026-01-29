package com.jackson.clientapi.service;

import com.jackson.clientapi.exception.ClientNotFoundException;
import com.jackson.clientapi.model.Client;
import com.jackson.clientapi.repository.ClientRepository;
import com.jackson.clientapi.dto.ClientCreateDTO;
import com.jackson.clientapi.dto.ClientUpdateDTO;
import com.jackson.clientapi.dto.ClientResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jackson.clientapi.exception.EmailAlreadyExistsException;


import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository repository,
                         PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE
    public ClientResponseDTO createClient(ClientCreateDTO dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        Client client = new Client();
        client.setNome(dto.getNome());
        client.setEmail(dto.getEmail());
        client.setSenha(passwordEncoder.encode(dto.getSenha()));

        Client saved = repository.save(client);
        return toResponseDTO(saved);
    }


    // READ ALL
    public List<ClientResponseDTO> listarClientes() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // READ BY ID
    public ClientResponseDTO buscarPorId(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return toResponseDTO(client);
    }

    // UPDATE
    public ClientResponseDTO updateClient(Long id, ClientUpdateDTO dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        if (dto.getNome() != null) client.setNome(dto.getNome());
        if (dto.getEmail() != null) client.setEmail(dto.getEmail());

        Client updated = repository.save(client);
        return toResponseDTO(updated);
    }

    // DELETE
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        repository.deleteById(id);
    }

    // Entity → DTO
    private ClientResponseDTO toResponseDTO(Client client) {
        return new ClientResponseDTO(client.getId(), client.getNome());
    }
}
