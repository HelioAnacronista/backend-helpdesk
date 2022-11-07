package io.helioanacronista.helpdesk.repositories;

import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    Optional<Tecnico> findByCpf(String cpf);

    Optional<Tecnico> findByEmail(String email);

//    @Query("SELECT obj FROM Product obj " +
//            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
//    Page<Tecnico> searchByName(String name, Pageable pageable);

    List<Tecnico> findAll();
}
