package com.jackson.clientapi.dto;

public class ClientResponseDTO {
    private Long id;
    private String nome;
    // Não expomos email ou outras informações sensíveis

    public ClientResponseDTO() {}

    public ClientResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
