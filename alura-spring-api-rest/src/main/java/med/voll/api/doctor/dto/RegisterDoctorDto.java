package med.voll.api.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.address.Address;
import med.voll.api.doctor.Speciality;

public record RegisterDoctorDto(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telephone,

        @NotNull
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Speciality speciality,

        @NotNull @Valid
        Address address) {
}
