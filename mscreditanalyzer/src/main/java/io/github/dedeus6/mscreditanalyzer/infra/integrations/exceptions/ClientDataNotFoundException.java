package io.github.dedeus6.mscreditanalyzer.infra.integrations.exceptions;

public class ClientDataNotFoundException extends  Exception {
    public ClientDataNotFoundException() {
        super("Client data not found for this CPF.");
    }
}
