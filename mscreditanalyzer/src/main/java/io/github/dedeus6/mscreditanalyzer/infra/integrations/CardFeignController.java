package io.github.dedeus6.mscreditanalyzer.infra.integrations;

import io.github.dedeus6.mscreditanalyzer.domain.model.Card;
import io.github.dedeus6.mscreditanalyzer.domain.model.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/api/v1/card")
public interface CardFeignController {

    @GetMapping
    ResponseEntity<List<CardClient>> getCardsByCpf(@RequestParam("cpf") String cpf);

    @GetMapping
    ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income);

}
