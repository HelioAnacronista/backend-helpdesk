package io.helioanacronista.helpdesk.DTO;

import io.helioanacronista.helpdesk.domain.entities.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private Long id;
    private String authority;

    public RoleDTO(Role entity) {
        id = entity.getId();
        authority = entity.getAuthority();
    }
}
