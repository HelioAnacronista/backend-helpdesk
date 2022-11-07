package io.helioanacronista.helpdesk.DTO;

import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Role;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TecnicoDTO implements Serializable {

    protected Integer id;

    @Size(min = 3, max = 80, message = "O nome precisa ter entre 3 a 8 caracteres")
    @NotNull(message = "O campo NOME é requerido")
    protected String nome;

    @NotNull(message = "O campo CPF é requerido")
    @CPF
    protected String cpf;

    @NotNull(message = "O campo EMAIL é requerido")
    @Email
    protected String email;

    @NotNull(message = "O campo SENHA é requerido")
    protected String senha;

    private Set<RoleDTO> roles = new HashSet<RoleDTO>();

    private LocalDate dataCriacao = LocalDate.now();

    private List<ChamadoDTO> chamados = new ArrayList<ChamadoDTO>();

    public TecnicoDTO(Tecnico entity) {
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
