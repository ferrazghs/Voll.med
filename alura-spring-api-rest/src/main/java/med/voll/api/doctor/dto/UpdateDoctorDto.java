package med.voll.api.doctor.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.Address;

public record UpdateDoctorDto(

        @NotNull
        Long id,
        String name,
        String telephone,
        Address address) {
}
