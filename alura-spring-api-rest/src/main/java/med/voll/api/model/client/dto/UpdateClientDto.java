package med.voll.api.model.client.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.address.Address;

public record UpdateClientDto(

        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
