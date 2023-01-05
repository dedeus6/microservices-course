package io.github.dedeus6.mscards.domain.service;

import io.github.dedeus6.mscards.domain.model.CardClient;
import io.github.dedeus6.mscards.infra.repository.CardClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardClientService {
    private final CardClientRepository cardClientRepository;

    public List<CardClient> listCardsByCpf(String cpf) {
        return cardClientRepository.findByCpf(cpf);
    }
}
