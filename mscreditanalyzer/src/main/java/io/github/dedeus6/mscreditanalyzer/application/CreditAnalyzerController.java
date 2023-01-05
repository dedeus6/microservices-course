package io.github.dedeus6.mscreditanalyzer.application;

import io.github.dedeus6.mscreditanalyzer.domain.model.AnalyzerData;
import io.github.dedeus6.mscreditanalyzer.domain.model.ClaimCardData;
import io.github.dedeus6.mscreditanalyzer.domain.model.services.CreditAnalyzerService;
import io.github.dedeus6.mscreditanalyzer.exceptions.ClaimCardException;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions.ClientDataNotFoundException;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit/analyzer")
@RequiredArgsConstructor
public class CreditAnalyzerController {

    private final CreditAnalyzerService creditAnalyzerService;

    @GetMapping(value = "/client/situation")
    public ResponseEntity getClientSituation(@RequestParam("cpf") String cpf) {
        try {
            var clientSituation = creditAnalyzerService.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (GenericException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity clientAnalyzer(@RequestBody AnalyzerData request) {
        try {
            var response = creditAnalyzerService.analyzer(request.getCpf(), request.getIncome());
            return ResponseEntity.ok(response);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (GenericException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("claim/card")
    public ResponseEntity claimCard(@RequestBody ClaimCardData request) {
        try {
            var protocol = creditAnalyzerService.claimCard(request);
            return ResponseEntity.ok(protocol);
        } catch (ClaimCardException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
