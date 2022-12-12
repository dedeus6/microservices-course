package io.github.dedeus6.mscards.infra.repository;

import io.github.dedeus6.mscards.domain.model.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardClientRepository extends JpaRepository<CardClient, Long> {
    List<CardClient> findByCpf(String cpf);
}
