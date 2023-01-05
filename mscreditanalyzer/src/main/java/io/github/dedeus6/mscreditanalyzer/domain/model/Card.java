package io.github.dedeus6.mscreditanalyzer.domain.model;

import io.github.dedeus6.mscreditanalyzer.domain.model.enums.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String name;
    private CardFlag flag;
    private BigDecimal basicLimit;
}
