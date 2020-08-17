package lrsystemsweb.com.br.savepetapp.domain.pedido.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.comun.StatusPedido;
import lrsystemsweb.com.br.savepetapp.domain.comun.TipoPedido;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEntradaNoAbrigo;

    @Column
    private String condicoesParaAdocao;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_pk")
    @NotNull(message = "Deve Informar o Usuario")
    private Usuario usuario;

    @Column(columnDefinition = "POINT")
    private Point ponto;

    @OneToOne(cascade=CascadeType.PERSIST)
    @NotNull(message = "Deve Informar o Animal")
    private Animal animal;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataInclusao;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataAlteracao;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAvistamentoAnimal;

    @Column
    private String pontoReferencia;

    @Column
    @NotNull(message = "Deve Informar Descrição do Pedido")
    private String descricao;

    @Column
    @NotNull(message = "Deve Informar o Pedido")
    private TipoPedido tipoPedido;

    @Column
    private StatusPedido statusPedido;

}
