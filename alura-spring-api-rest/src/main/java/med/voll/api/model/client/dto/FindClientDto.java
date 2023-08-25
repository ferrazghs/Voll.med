package med.voll.api.model.client.dto;

import med.voll.api.model.client.Client;

public record FindClientDto(Long id, String name, String email, String cpf) {

    public FindClientDto(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getCpf());
    }
}
