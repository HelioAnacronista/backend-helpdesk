package io.helioanacronista.helpdesk.services;

import io.helioanacronista.helpdesk.DTO.TecnicoDTO;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import io.helioanacronista.helpdesk.repositories.TecnicoRepository;
import io.helioanacronista.helpdesk.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        Tecnico newDTO = new Tecnico(dto);
        return repository.save(newDTO);
    }
}
