package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.doctor.Doctor;
import med.voll.api.model.doctor.dto.DetailsDoctorDto;
import med.voll.api.model.doctor.dto.FindDoctorDto;
import med.voll.api.model.doctor.dto.RegisterDoctorDto;
import med.voll.api.model.doctor.dto.UpdateDoctorDto;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterDoctorDto registerDoctorDto, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(registerDoctorDto);
        repository.save(doctor);

        var uri = uriBuilder.path("doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DetailsDoctorDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<FindDoctorDto>> findDoctor(@PageableDefault(sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByStatusTrue(pageable).map(FindDoctorDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid UpdateDoctorDto updateDoctorDto) {
        var doctor = repository.getReferenceById(updateDoctorDto.id());
        doctor.updateRegisterDoctor(updateDoctorDto);

        return ResponseEntity.ok(new DetailsDoctorDto(doctor));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.inactivateDoctor();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detailsDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetailsDoctorDto(doctor));
    }
}
