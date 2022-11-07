package io.helioanacronista.helpdesk.services;

import io.helioanacronista.helpdesk.DTO.ChamadoDTO;
import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Cliente;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import io.helioanacronista.helpdesk.domain.enums.Prioridade;
import io.helioanacronista.helpdesk.domain.enums.Status;
import io.helioanacronista.helpdesk.repositories.ChamadoRepository;
import io.helioanacronista.helpdesk.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public ChamadoDTO findById(Integer id) {
        Chamado entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
        return new ChamadoDTO(entity);
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO dto) {
        Chamado entity = new Chamado();
        copyDTOeEntity(dto, entity);
        return repository.save(entity);
    }

    public Chamado update(Integer id, ChamadoDTO dto) {
        Chamado entity = new Chamado();
        dto.setId(id);
        copyDTOeEntity(dto, entity);
        return repository.save(entity);
    }

    private Chamado copyDTOeEntity(ChamadoDTO dto, Chamado entity) {
        entity.setDataAbertura(dto.getDataAbertura());
        entity.setDataFechamento(dto.getDataFechamento());

        Tecnico tecnicoEntity = tecnicoService.findById(dto.getTecnicoId());
        Cliente clienteEntity = clienteService.findById(dto.getClienteId());

        if(dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if(dto.getStatus().equals(2)) {
            entity.setDataFechamento(LocalDate.now());
        }


        try {
            entity.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
            entity.setStatus(Status.toEnum(dto.getStatus()));
        } catch (IllegalAccessException e) {
            Runnable runnable = () -> new ResourceNotFoundException("Enum errado!");
        }
        entity.setTecnico(tecnicoEntity);
        entity.setCliente(clienteEntity);

        entity.getTecnico().setNome(dto.getNomeTecnico());
        entity.getCliente().setNome(dto.getNomeCliente());

        entity.setTitulo(dto.getTitulo());
        entity.setObservacoes(dto.getObservacoes());
        return entity;
    }
}
