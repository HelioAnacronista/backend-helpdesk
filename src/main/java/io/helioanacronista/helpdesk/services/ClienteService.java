//package io.helioanacronista.helpdesk.services;
//
//import io.helioanacronista.helpdesk.DTO.ClienteDTO;
//import io.helioanacronista.helpdesk.domain.entities.Cliente;
//import io.helioanacronista.helpdesk.domain.entities.Pessoa;
//import io.helioanacronista.helpdesk.repositories.ClienteRepository;
//import io.helioanacronista.helpdesk.repositories.PessoaRepository;
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
//public class ClienteService {
//
//    @Autowired
//    private ClienteRepository repository;
//    @Autowired
//    private PessoaRepository pessoaRepository;
//
//    public Cliente findById(Integer id) {
//        Optional<Cliente> obj = repository.findById(id);
//        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
//    }
//
//    public List<Cliente> findAll() {
//        return repository.findAll();
//    }
//
//    public Cliente create(ClienteDTO dto) {
//        dto.setId(null);
//        validarPorCPFeEmail(dto);
//        Cliente entity = new Cliente(dto);
//        return repository.save(entity);
//    }
//
//    public Cliente update(Integer id, @Valid ClienteDTO dto) {
//        dto.setId(id);
//        Cliente entity = findById(id);
//        validarPorCPFeEmail(dto);
//        entity = new Cliente(dto);
//        return repository.save(entity);
//    }
//
//    public void delete(Integer id) {
//        Cliente obj = findById(id);
//
//        if (obj.getChamados().size() > 0) {
//            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
//        }
//
//        repository.deleteById(id);
//    }
//
//    private void validarPorCPFeEmail(ClienteDTO dto) {
//        Optional<Pessoa> entity = pessoaRepository.findByCpf(dto.getCpf());
//        if (entity.isPresent() && entity.get().getId() != dto.getId()) {
//            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
//        }
//
//        entity = pessoaRepository.findByEmail(dto.getEmail());
//        if (entity.isPresent() && entity.get().getId() != dto.getId()) {
//            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
//        }
//    }
//}
