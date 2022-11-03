//package io.helioanacronista.helpdesk.services;
//
//import io.helioanacronista.helpdesk.DTO.ChamadoDTO;
//import io.helioanacronista.helpdesk.domain.entities.Chamado;
//import io.helioanacronista.helpdesk.domain.entities.Cliente;
//import io.helioanacronista.helpdesk.domain.entities.Tecnico;
//import io.helioanacronista.helpdesk.domain.enums.Prioridade;
//import io.helioanacronista.helpdesk.domain.enums.Status;
//import io.helioanacronista.helpdesk.repositories.ChamadoRepository;
//import io.helioanacronista.helpdesk.services.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ChamadoService {
//
//    @Autowired
//    private ChamadoRepository repository;
//    @Autowired
//    private TecnicoService tecnicoService;
//    @Autowired
//    private ClienteService clienteService;
//
//    public Chamado findById(Integer id) {
//        Optional<Chamado> entity = repository.findById(id);
//        return entity.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
//    }
//
//    public List<Chamado> findAll() {
//        return repository.findAll();
//    }
//
//    public Chamado create(ChamadoDTO dto) {
//        return repository.save(salvarNovoChamado(dto));
//    }
//
//    public Chamado update(Integer id, ChamadoDTO objDTO) {
//        objDTO.setId(id);
//        Chamado oldObj = findById(id);
//        oldObj = salvarNovoChamado(objDTO);
//        return repository.save(oldObj);
//    }
//
//    private Chamado salvarNovoChamado(ChamadoDTO dto) {
//        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
//        Cliente cliente = clienteService.findById(dto.getCliente());
//
//        Chamado chamado = new Chamado();
//        if(dto.getId() != null) {
//            chamado.setId(dto.getId());
//        }
//        if(dto.getStatus().equals(2)) {
//            chamado.setDataFechamento(LocalDate.now());
//        }
//
//        chamado.setTecnico(tecnico);
//        chamado.setCliente(cliente);
//        try {
//            chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
//            chamado.setStatus(Status.toEnum(dto.getStatus()));
//        } catch (IllegalAccessException e) {
//            Runnable runnable = () -> new ResourceNotFoundException("Enum errado!");
//        }
//
//        chamado.setTitulo(dto.getTitulo());
//        chamado.setObservacoes(dto.getObservacoes());
//        return chamado;
//    }
//}
