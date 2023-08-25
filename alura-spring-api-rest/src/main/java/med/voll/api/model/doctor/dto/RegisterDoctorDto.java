package med.voll.api.model.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.model.address.Address;
import med.voll.api.model.doctor.Speciality;

public record RegisterDoctorDto(
        @NotBlank
        String name,

        @NotBlank
        @Email(message = "{email.invalido}")
        String email,

        @NotBlank
        String telephone,

        @NotNull
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,

        @NotNull
        Speciality speciality,

        @NotNull @Valid
        Address address) {
}
