package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.dto.FindDoctorDto;
import med.voll.api.doctor.dto.RegisterDoctorDto;
import med.voll.api.doctor.dto.UpdateDoctorDto;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid RegisterDoctorDto registerDoctorDto) {
        repository.save(new Doctor(registerDoctorDto));
    }

    @GetMapping
    public Page<FindDoctorDto> findDoctor(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return repository.findAllByStatusTrue(pageable).map(FindDoctorDto::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDto updateDoctorDto) {
         Doctor doctor = repository.getReferenceById(updateDoctorDto.id());
         doctor.updateRegisterDoctor(updateDoctorDto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.inactivateDoctor();
    }
}
