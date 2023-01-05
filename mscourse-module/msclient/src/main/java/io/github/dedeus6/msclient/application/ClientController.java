package io.github.dedeus6.msclient.application;

import io.github.dedeus6.msclient.application.model.request.ClientSaveRequest;
import io.github.dedeus6.msclient.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request) {
        log.info("Creating a new client - " + request);
        var client = request.toModel();
        clientService.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity getByCpf(@RequestParam("cpf") String cpf) {
        log.info("Getting client - cpf: " + cpf);
        var client = clientService.getByCpf(cpf);
        if(client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

}
