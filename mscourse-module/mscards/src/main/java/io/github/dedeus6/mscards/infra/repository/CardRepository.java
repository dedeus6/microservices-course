package io.github.dedeus6.mscards.infra.repository;

import io.github.dedeus6.mscards.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByIncomeLessThanEqual(BigDecimal income);
}
