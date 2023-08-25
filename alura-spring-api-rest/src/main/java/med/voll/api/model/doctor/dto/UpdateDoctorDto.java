package med.voll.api.model.doctor.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.address.Address;

public record UpdateDoctorDto(

        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
