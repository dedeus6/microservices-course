package io.github.dedeus6.msclient.application.model.request;

import io.github.dedeus6.msclient.domain.model.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Client toModel() {
        return new Client(cpf, name, age);
    }
}
