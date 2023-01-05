package io.github.dedeus6.mscreditanalyzer.infra.integrations;

import io.github.dedeus6.mscreditanalyzer.domain.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclient", path = "/api/v1/client")
public interface ClientFeignController {

    @GetMapping
    ResponseEntity<ClientData> getByCpf(@RequestParam("cpf") String cpf);
}
