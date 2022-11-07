package io.helioanacronista.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.helioanacronista.helpdesk.domain.entities.Chamado;
import io.helioanacronista.helpdesk.domain.entities.Cliente;
import io.helioanacronista.helpdesk.domain.entities.Tecnico;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChamadoDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;
    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    @NotNull(message = "O campo TECNICO é requerido")
    private Integer tecnicoId;
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer clienteId;

    private String nomeTecnico;

    private String nomeCliente;

    public ChamadoDTO(Chamado entity) {
        this.id = entity.getId();
        this.dataAbertura = entity.getDataAbertura();
        this.dataFechamento = entity.getDataFechamento();
        this.prioridade = entity.getPrioridade().getCodigo();
        this.status = entity.getStatus().getCodigo();
        this.titulo = entity.getTitulo();
        this.observacoes = entity.getObservacoes();
        this.tecnicoId = entity.getTecnico().getId();
        this.clienteId = entity.getCliente().getId();
        this.nomeCliente = entity.getCliente().getNome();
        this.nomeTecnico = entity.getTecnico().getNome();
    }
}
