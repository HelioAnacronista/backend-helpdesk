package io.helioanacronista.helpdesk.DTO;

import io.helioanacronista.helpdesk.domain.entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private Integer id;
    private String email;
    private String senha;
    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        senha = entity.getSenha();
        for (GrantedAuthority role : entity.getAuthorities()) {
            roles.add(role.getAuthority());
        }
    }
}
