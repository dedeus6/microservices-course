package io.github.dedeus6.mscreditanalyzer.domain.model;

import io.github.dedeus6.mscreditanalyzer.domain.model.enums.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCards {
    private String card;
    private CardFlag flag;
    private BigDecimal approvedLimit;
}
