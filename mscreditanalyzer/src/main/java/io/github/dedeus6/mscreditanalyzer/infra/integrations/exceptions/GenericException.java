package io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions;

import lombok.Getter;

public class GenericException extends Exception {

    @Getter
    private Integer status;

    public GenericException(String error, Integer status) {
        super(error);
        this.status = status;
    }
}
