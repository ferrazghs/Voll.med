package med.voll.api.client.dto;

import med.voll.api.address.Address;
import med.voll.api.client.Client;

public record DetailsClientDto(
        Long id,
        String name,
        String email,
        String telephone,
        String cpf,
        Address address) {

    public DetailsClientDto(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getTelephone(), client.getCpf(), client.getAddress());
    }
}




