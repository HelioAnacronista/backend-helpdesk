package io.helioanacronista.helpdesk.DTO;


import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Role;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TecnicoCreateDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private Set<RoleDTO> roles = new HashSet<RoleDTO>();

    private LocalDate dataCriacao = LocalDate.now();

    private List<ChamadoDTO> chamados = new ArrayList<ChamadoDTO>();

    public TecnicoCreateDTO(Tecnico entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        email = entity.getEmail();
        senha = entity.getSenha();
        for (Role rol : entity.getRoles()) {
            roles.add(new RoleDTO(rol));
        }
        dataCriacao = entity.getDataCriacao();
        for (Chamado chama : entity.getChamados()) {
            chamados.add(new ChamadoDTO(chama));
        }
    }
}
