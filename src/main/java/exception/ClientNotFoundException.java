package com.jackson.clientapi.exception;

public class ClientNotFoundException extends RuntimeException {

    // Construtor que recebe o ID do cliente
    public ClientNotFoundException(Long id) {
        super("Cliente não encontrado com ID: " + id);
    }

    // Construtor genérico que recebe uma mensagem customizada
    public ClientNotFoundException(String message) {
        super(message);
    }
}
