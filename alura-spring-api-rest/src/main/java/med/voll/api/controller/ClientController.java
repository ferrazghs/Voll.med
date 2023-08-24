package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.client.Client;
import med.voll.api.client.dto.DetailsClientDto;
import med.voll.api.client.dto.FindClientDto;
import med.voll.api.client.dto.RegisterClientDto;
import med.voll.api.client.dto.UpdateClientDto;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterClientDto registerClientDto, UriComponentsBuilder uriBuilder) {
        var client = new Client(registerClientDto);
        repository.save(client);

        var uri = uriBuilder.path("client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsClientDto(client));
    }

    @GetMapping
    public ResponseEntity<Page<FindClientDto>> findClient(@PageableDefault(sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByStatusTrue(pageable).map(FindClientDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateClient(@RequestBody @Valid UpdateClientDto updateClientDto) {
        var client = repository.getReferenceById(updateClientDto.id());
        client.updateRegisterClient(updateClientDto);

        return ResponseEntity.ok(new DetailsClientDto(client));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var client = repository.getReferenceById(id);
        client.inactivateClient();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detailsClient(@PathVariable Long id) {
        var client = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetailsClientDto(client));
    }
}
