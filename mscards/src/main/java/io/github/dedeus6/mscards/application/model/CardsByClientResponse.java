package io.github.dedeus6.mscards.application.model;

import io.github.dedeus6.mscards.domain.model.CardClient;
import io.github.dedeus6.mscards.domain.model.CardFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String name;
    private CardFlag flag;
    private BigDecimal basicLimit;

    public static CardsByClientResponse fromModel(CardClient model) {
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getFlag(),
                model.getLimit()
        );
    }

}
