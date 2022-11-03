package io.helioanacronista.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.helioanacronista.helpdesk.domain.entities.Role;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClienteDTO implements Serializable {

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

    private Set<Role> roles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();


}
