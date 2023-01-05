package io.github.dedeus6.msclient.domain.service;

import io.github.dedeus6.msclient.domain.model.Client;
import io.github.dedeus6.msclient.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }
}
