package io.github.dedeus6.mscards.application.model;

import io.github.dedeus6.mscards.domain.model.Card;
import io.github.dedeus6.mscards.domain.model.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel() {
        return new Card(name, flag, income, basicLimit);
    }
}
