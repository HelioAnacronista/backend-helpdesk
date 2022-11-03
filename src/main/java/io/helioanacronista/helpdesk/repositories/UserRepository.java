package io.helioanacronista.helpdesk.repositories;

import io.helioanacronista.helpdesk.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
