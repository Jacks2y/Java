package com.jackson.clientapi.repository;

import com.jackson.clientapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Verifica se já existe cliente com o email informado
    boolean existsByEmail(String email);

    // Útil futuramente para login (email + senha)
    Optional<Client> findByEmail(String email);
}
