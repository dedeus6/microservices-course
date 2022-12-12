package io.github.dedeus6.mscards.domain.service;

import io.github.dedeus6.mscards.domain.model.Card;
import io.github.dedeus6.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card card) {
        return this.cardRepository.save(card);
    }

    public List<Card> getCardByIncomeLessOrEqual(Long income) {
        var bigDecimalIncome = BigDecimal.valueOf(income);
        return this.cardRepository.findByIncomeLessThanEqual(bigDecimalIncome);
    }

}
