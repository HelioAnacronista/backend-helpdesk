package io.helioanacronista.helpdesk.DTO;

import io.helioanacronista.helpdesk.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class userDTO {

    private Long id;
    private String name;
    private String email;
    private List<String> roles = new ArrayList<>();

//    public UserDTO(User entity) {
//        id = entity.getId();
//        name = entity.getName();
//        email = entity.getEmail();
//        for (GrantedAuthority role : entity.getAuthorities()) {
//            roles.add(role.getAuthority());
//        }
//    }
}
