package med.voll.api.model.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.address.Address;
import med.voll.api.model.doctor.dto.RegisterDoctorDto;
import med.voll.api.model.doctor.dto.UpdateDoctorDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Doctor")
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    private Boolean status;

    public Doctor(RegisterDoctorDto doctor) {
        this.status = true;
        this.name = doctor.name();
        this.email = doctor.email();
        this.telephone = doctor.telephone();
        this.crm = doctor.crm();
        this.speciality = doctor.speciality();
        this.address = new Address(doctor.address());
    }

    public void updateRegisterDoctor(UpdateDoctorDto doctor) {
        if (doctor.name() != null) {
            this.name = doctor.name();
        }

        if (doctor.telephone() != null) {
            this.telephone = doctor.telephone();
        }

        if (doctor.address() != null) {
            this.address.updateRegisterAddress(doctor.address());
        }
    }

    public void inactivateDoctor() {
        this.status = false;
    }
}
