package io.github.dedeus6.mscreditanalyzer.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dedeus6.mscreditanalyzer.domain.model.ClaimCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardsIssue;

    public void claimCard(ClaimCardData request) throws JsonProcessingException {
        var json = jsonToString(request);

        rabbitTemplate.convertAndSend(queueCardsIssue.getName(), json);
    }

    private String jsonToString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        var json = mapper.writeValueAsString(object);
        return json;
    }
}
