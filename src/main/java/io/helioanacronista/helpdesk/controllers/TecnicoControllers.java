package io.helioanacronista.helpdesk.controllers;

import io.helioanacronista.helpdesk.DTO.TecnicoCreateDTO;
import io.helioanacronista.helpdesk.DTO.TecnicoDTO;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import io.helioanacronista.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoControllers {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        TecnicoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>>  findAll (){
        List<TecnicoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TecnicoCreateDTO> create (@Valid @RequestBody TecnicoCreateDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
