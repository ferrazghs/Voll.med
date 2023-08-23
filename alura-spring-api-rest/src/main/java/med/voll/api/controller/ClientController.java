package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.client.Client;
import med.voll.api.client.dto.FindClientDto;
import med.voll.api.client.dto.RegisterClientDto;
import med.voll.api.client.dto.UpdateClientDto;
import med.voll.api.doctor.Doctor;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid RegisterClientDto client) {
        repository.save(new Client(client));
    }

    @GetMapping
    public Page<FindClientDto> findClient(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return repository.findAllByStatusTrue(pageable).map(FindClientDto::new);
    }

    @PutMapping
    @Transactional
    public void updateClient(@RequestBody @Valid UpdateClientDto updateClientDto) {
        Client client = repository.getReferenceById(updateClientDto.id());
        client.updateRegisterClient(updateClientDto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Client client = repository.getReferenceById(id);
        client.inactivateClient();
    }
}
