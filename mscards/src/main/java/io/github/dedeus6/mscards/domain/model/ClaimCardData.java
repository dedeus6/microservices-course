package io.github.dedeus6.mscards.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClaimCardData {
    private Long id;
    private String cpf;
    private String adress;
    private BigDecimal approvedLimit;
}