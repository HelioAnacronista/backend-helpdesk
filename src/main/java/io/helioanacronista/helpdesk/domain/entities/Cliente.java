package io.helioanacronista.helpdesk.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.helioanacronista.helpdesk.DTO.ClienteDTO;
import io.helioanacronista.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa {


    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO dto) {
        super();
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.perfis = dto.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = dto.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
