package io.github.dedeus6.msclient.infra.repository;

import io.github.dedeus6.msclient.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
