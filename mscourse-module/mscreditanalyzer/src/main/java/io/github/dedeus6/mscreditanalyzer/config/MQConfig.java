package io.github.dedeus6.mscreditanalyzer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.cards-issue}")
    private String queueCardIssue;

    @Bean
    public Queue queueCardIssue() {
        return new Queue(queueCardIssue, true);
    }
}
