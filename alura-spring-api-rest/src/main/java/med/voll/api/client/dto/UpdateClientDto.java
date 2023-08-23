package med.voll.api.client.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.Address;

public record UpdateClientDto(

        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
