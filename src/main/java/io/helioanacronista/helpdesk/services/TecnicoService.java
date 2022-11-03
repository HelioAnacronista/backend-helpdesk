//package io.helioanacronista.helpdesk.services;
//
//import io.helioanacronista.helpdesk.DTO.TecnicoDTO;
//import io.helioanacronista.helpdesk.domain.entities.Pessoa;
//import io.helioanacronista.helpdesk.domain.entities.Tecnico;
//import io.helioanacronista.helpdesk.repositories.PessoaRepository;
//import io.helioanacronista.helpdesk.repositories.TecnicoRepository;
//import io.helioanacronista.helpdesk.services.exceptions.DataIntegrityViolationException;
//import io.helioanacronista.helpdesk.services.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TecnicoService {
//
//    @Autowired
//    private TecnicoRepository repository;
//
//    @Autowired
//    private PessoaRepository pessoaRepository;
//
//    public Tecnico findById(Integer id) {
//        Optional<Tecnico> obj = repository.findById(id);
//        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
//    }
//
//    public List<Tecnico> findAll() {
//        return repository.findAll();
//    }
//
//    public Tecnico create(TecnicoDTO dto) {
//        dto.setId(null);
//        validarPorCPFeEmail(dto);
//        Tecnico entity = new Tecnico(dto);
//        return repository.save(entity);
//    }
//
//    public Tecnico update(Integer id, @Valid TecnicoDTO dto) {
//        dto.setId(id);
//        Tecnico entity = findById(id);
//        validarPorCPFeEmail(dto);
//        entity = new Tecnico(dto);
//        return repository.save(entity);
//    }
//
//    public void delete(Integer id) {
//        Tecnico entity = findById(id);
//
//        if (entity.getChamados().size() > 0) {
//            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
//        }
//        repository.deleteById(id);
//    }
//
//    private void validarPorCPFeEmail(TecnicoDTO dto) {
//        Optional<Pessoa> entity = pessoaRepository.findByCpf(dto.getCpf());
//        if (entity.isPresent() && entity.get().getId() != dto.getId()) {
//            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
//        }
//
//        entity = pessoaRepository.findByEmail(dto.getEmail());
//        if(entity.isPresent() && entity.get().getId() != dto.getId()) {
//            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
//        }
//    }
//}
