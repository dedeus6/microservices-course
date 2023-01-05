package io.github.dedeus6.mscards.application;

import io.github.dedeus6.mscards.application.model.CardSaveRequest;
import io.github.dedeus6.mscards.application.model.CardsByClientResponse;
import io.github.dedeus6.mscards.domain.model.Card;
import io.github.dedeus6.mscards.domain.service.CardClientService;
import io.github.dedeus6.mscards.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardClientService cardClientService;

    @PostMapping
    public ResponseEntity save(@RequestBody CardSaveRequest request) {
        var card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income) {
        var cardByIncomeLessOrEqual = cardService.getCardByIncomeLessOrEqual(income);
        return ResponseEntity.ok(cardByIncomeLessOrEqual);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByCpf(@RequestParam("cpf") String cpf) {
         var cardClients = cardClientService.listCardsByCpf(cpf);
         var listToResponse = cardClients.stream()
                 .map(CardsByClientResponse::fromModel)
                 .collect(Collectors.toList());
         return ResponseEntity.ok(listToResponse);
    }

}
