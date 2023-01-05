package io.github.dedeus6.mscreditanalyzer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnalyzerDataResponse {
    List<ApprovedCards> approvedCards;
}
