package io.helioanacronista.helpdesk.repositories;

import io.helioanacronista.helpdesk.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
