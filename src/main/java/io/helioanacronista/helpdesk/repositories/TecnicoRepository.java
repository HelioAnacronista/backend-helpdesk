package io.helioanacronista.helpdesk.repositories;

import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
