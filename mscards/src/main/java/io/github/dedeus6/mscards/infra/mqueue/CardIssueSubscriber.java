package io.github.dedeus6.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dedeus6.mscards.domain.model.CardClient;
import io.github.dedeus6.mscards.domain.model.ClaimCardData;
import io.github.dedeus6.mscards.infra.repository.CardClientRepository;
import io.github.dedeus6.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardIssueSubscriber {

    private final CardRepository cardRepository;
    private final CardClientRepository cardClientRepository;

    @RabbitListener(queues = "${mq.queues.cards-issue}")
    public void receiver(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            var data = mapper.readValue(payload, ClaimCardData.class);
            var card = cardRepository.findById(data.getId()).orElseThrow();
            var cardClient = new CardClient();
            cardClient.setCard(card);
            cardClient.setCpf(data.getCpf());
            cardClient.setCardClientLimit(data.getApprovedLimit());

            cardClientRepository.save(cardClient);

        } catch (JsonProcessingException e) {
            log.error("Error when received the card emission: {}", e.getMessage());
        }
    }

}
