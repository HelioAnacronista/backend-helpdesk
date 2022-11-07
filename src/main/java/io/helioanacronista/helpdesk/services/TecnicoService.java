package io.helioanacronista.helpdesk.services;

import io.helioanacronista.helpdesk.DTO.ChamadoDTO;
import io.helioanacronista.helpdesk.DTO.RoleDTO;
import io.helioanacronista.helpdesk.DTO.TecnicoCreateDTO;
import io.helioanacronista.helpdesk.DTO.TecnicoDTO;
import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Role;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import io.helioanacronista.helpdesk.repositories.TecnicoRepository;
import io.helioanacronista.helpdesk.services.exceptions.DataIntegrityViolationException;
import io.helioanacronista.helpdesk.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public TecnicoDTO findById(Integer id) {
        Tecnico tecnico = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
        return new TecnicoDTO(tecnico);
    }

    public List<TecnicoDTO> findAll() {
        List<Tecnico> result = repository.findAll();
        return result.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
    }

    public TecnicoCreateDTO insert(TecnicoCreateDTO dto) {
        if (dto.getRoles().isEmpty()) {
            Tecnico entity = new Tecnico();
            //Presets inicias e verificaçoes
            dto.setId(null);
            validarPorCPFeEmail(dto);

            //copia o dto e converter em entidade
            entity.setNome(dto.getNome());
            entity.setCpf(dto.getCpf());
            entity.setEmail(dto.getEmail());
            entity.setSenha(passwordEncoder.encode(dto.getSenha()));

            //Cria um role padrão para entidade
            Set<Role> setRoles = new HashSet<>();
            setRoles.add(new Role(1L, "ROLE_CLIENT"));

            entity.setDataCriacao(dto.getDataCriacao());
            entity.setChamados(new ArrayList<>());

            //Save a entidade
            entity = repository.save(entity);
            return new TecnicoCreateDTO(entity);
        } else {
            Tecnico entity = new Tecnico();
            dto.setId(null);
            validarPorCPFeEmail(dto);

            copyDTOToEntity(dto, entity);

            entity = repository.save(entity);

            return new TecnicoCreateDTO(entity);
        }

    }

    public TecnicoDTO update(Integer id, @Valid TecnicoDTO dto) {
        try {
            dto.setId(id);
            Tecnico entity = repository.getReferenceById(id);
            copyDTOToEntityUpdate(dto, entity);
            entity = repository.save(entity);
            return new TecnicoDTO(entity);

        }  catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recuso não encontrado");
        }
    }

    public void delete(Integer id) {
        try {
            TecnicoDTO entity = findById(id);
            if (entity.getChamados().size() > 0) {
                throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recuso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade refencial");
        }

        repository.deleteById(id);
    }

    /*
    Métodos de validação e de copias de dto
     */

    //Validador de CPF e EMAIL
    private void validarPorCPFeEmail(TecnicoDTO dto) {
        Optional<Tecnico> entity = repository.findByCpf(dto.getCpf());
        if (entity.isPresent() && entity.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        entity = repository.findByEmail(dto.getEmail());
        if(entity.isPresent() && entity.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    //Validador de CPF e EMAIL
    private void validarPorCPFeEmail(TecnicoCreateDTO dto) {
        Optional<Tecnico> entity = repository.findByCpf(dto.getCpf());
        if (entity.isPresent() && entity.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        entity = repository.findByEmail(dto.getEmail());
        if(entity.isPresent() && entity.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }



    //Copia Entity para DTO - para  método de Criação
    private void copyDTOToEntity (TecnicoCreateDTO dto, Tecnico entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setSenha(passwordEncoder.encode(dto.getSenha()));

        entity.getRoles().clear();
        for (RoleDTO rolDTO : dto.getRoles() ) {
            Role roles = new Role();
            rolDTO.setId(rolDTO.getId());
            roles.setId(1L);
            roles.setAuthority(rolDTO.getAuthority());
            entity.getRoles().add(roles);
        }

        entity.setDataCriacao(dto.getDataCriacao());

        if (dto.getChamados().isEmpty()) {
            entity.setChamados(new ArrayList<>());
        } else {
            for (ChamadoDTO chamaDTO : dto.getChamados()) {
                Chamado chamado = new Chamado();
                chamaDTO.setId(chamaDTO.getId());
                entity.getChamados().add(chamado);
            }
        }


    }

    //Copia os dados do dto para entidade do Método UPDATE
    private void copyDTOToEntityUpdate (TecnicoDTO dto, Tecnico entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setSenha(passwordEncoder.encode(dto.getSenha()));

        if (dto.getRoles().isEmpty()) {
            for (RoleDTO rolDTO : dto.getRoles() ) {
                Role roles = new Role();
                rolDTO.setId(rolDTO.getId());
                roles.setId(1L);
                roles.setAuthority(rolDTO.getAuthority());
                entity.getRoles().add(roles);
            }
        } else {
            for (RoleDTO roleDT : dto.getRoles()) {
                Role role = new Role();
                role.setId(roleDT.getId());
                role.setAuthority(roleDT.getAuthority());
                entity.getRoles().add(role);
            }
        }


        if (dto.getChamados().isEmpty()) {
            entity.setChamados(new ArrayList<>());
        } else {
            for (ChamadoDTO chamaDTO : dto.getChamados()) {
                Chamado chamado = new Chamado();
                chamaDTO.setId(chamaDTO.getId());
                entity.getChamados().add(chamado);
            }
        }
    }
}
