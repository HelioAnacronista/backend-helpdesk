package io.helioanacronista.helpdesk.repositories;

import io.helioanacronista.helpdesk.domain.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
