package med.voll.api.doctor.dto;

import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.Speciality;

public record FindDoctorDto(Long id, String name, String email, String crm, Speciality speciality) {

    public FindDoctorDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
