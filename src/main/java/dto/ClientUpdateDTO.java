package com.jackson.clientapi.dto;

import jakarta.validation.constraints.Email;

public class ClientUpdateDTO {
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    public ClientUpdateDTO() {}

    public ClientUpdateDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
