package io.github.dedeus6.mscreditanalyzer.domain.model.services;

import feign.FeignException;
import io.github.dedeus6.mscreditanalyzer.domain.model.*;
import io.github.dedeus6.mscreditanalyzer.exceptions.ClaimCardException;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.CardFeignController;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.ClientFeignController;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions.ClientDataNotFoundException;
import io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions.GenericException;
import io.github.dedeus6.mscreditanalyzer.infra.mqueue.CardIssuePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAnalyzerService {

    private final ClientFeignController clientFeignController;
    private final CardFeignController cardFeignController;
    private final CardIssuePublisher cardIssuePublisher;

    public ClientSituation getClientSituation(String cpf) throws ClientDataNotFoundException, GenericException {
        try {
            var clientDataResponse = clientFeignController.getByCpf(cpf);
            var cardsDataResponse = cardFeignController.getCardsByCpf(cpf);

            return ClientSituation.builder()
                    .client(clientDataResponse.getBody())
                    .cards(cardsDataResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (Objects.equals(HttpStatus.NOT_FOUND.value(), status))
                throw new ClientDataNotFoundException();

            throw new GenericException(e.getMessage(), status);
        }
    }

    public AnalyzerDataResponse analyzer(String cpf, Long income) throws ClientDataNotFoundException, GenericException {
        try {
            var clientDataResponse = clientFeignController.getByCpf(cpf);
            var cardsResponse = cardFeignController.getCardsByIncome(income);

            var cards = cardsResponse.getBody();
            var approvedCard = cards.stream().map(card -> {
                var clientData = clientDataResponse.getBody();
                var basicLimit = card.getBasicLimit();
                var age = BigDecimal.valueOf(clientData.getAge());
                var factor = age.divide(BigDecimal.valueOf(10));
                var approvedLimit = factor.multiply(basicLimit);

                var approvedCards = new ApprovedCards();
                approvedCards.setCard(card.getName());
                approvedCards.setFlag(card.getFlag());
                approvedCards.setApprovedLimit(approvedLimit);

                return approvedCards;
            }).collect(Collectors.toList());

            return new AnalyzerDataResponse(approvedCard);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (Objects.equals(HttpStatus.NOT_FOUND.value(), status))
                throw new ClientDataNotFoundException();

            throw new GenericException(e.getMessage(), status);
        }
    }

    public ProtocolClaimCard claimCard(ClaimCardData data) {
        try {
            cardIssuePublisher.claimCard(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolClaimCard(protocol);
        } catch (Exception e) {
            throw new ClaimCardException(e.getMessage());
        }
    }
}
