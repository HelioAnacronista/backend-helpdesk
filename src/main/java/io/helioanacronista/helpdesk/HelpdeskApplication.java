package io.helioanacronista.helpdesk;

import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Cliente;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import io.helioanacronista.helpdesk.domain.enums.Perfil;
import io.helioanacronista.helpdesk.domain.enums.Prioridade;
import io.helioanacronista.helpdesk.domain.enums.Status;
import io.helioanacronista.helpdesk.repositories.ChamadoRepository;
import io.helioanacronista.helpdesk.repositories.ClienteRepository;
import io.helioanacronista.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico myTecnico = new Tecnico(null, "Valdir cezar", "63653230268", "valdir@mail.com", "123");
		myTecnico.addPerfil(Perfil.ADMIN);

		Cliente firstCliente = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", myTecnico, firstCliente);

		tecnicoRepository.saveAll(Arrays.asList(myTecnico));
		clienteRepository.saveAll(Arrays.asList(firstCliente));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
