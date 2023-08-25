package med.voll.api.model.doctor.dto;

import med.voll.api.model.address.Address;
import med.voll.api.model.doctor.Doctor;
import med.voll.api.model.doctor.Speciality;

public record DetailsDoctorDto(
        Long id,
        String name,
        String email,
        String telephone,
        String crm,
        Speciality speciality,
        Address address) {

    public DetailsDoctorDto(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getTelephone(),
                doctor.getCrm(),
                doctor.getSpeciality(),
                doctor.getAddress());
    }
}



