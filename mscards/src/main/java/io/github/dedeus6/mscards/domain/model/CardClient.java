package io.github.dedeus6.mscards.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class CardClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "idCard", referencedColumnName = "id")
    private Card card;

    private BigDecimal limit;
}

