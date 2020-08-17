package lrsystemsweb.com.br.savepetapp.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.core.util.DataUtil;
import lrsystemsweb.com.br.savepetapp.domain.comun.TipoPedido;
import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {

    private Long pk;
    private UsuarioDTO usuario;
    private PontoDTO ponto;
    private AnimalDTO animal;
    private String descricao;
    private String dataAvistamentoAnimal;
    private String pontoReferencia;
    private TipoPedido tipoPedido;
    private String dataEntradaNoAbrigo;
    private String condicoesParaAdocao;
    private String dataInclusao;
    private String dataAlteracao;

    public PedidoDTO(Pedido p) {
        this.pk = p.getPk();
        this.usuario = new UsuarioDTO(p.getUsuario());
        this.ponto = new PontoDTO(p.getPonto());
        this.animal = new AnimalDTO(p.getAnimal());
        this.descricao = p.getDescricao();
        this.dataAvistamentoAnimal = DataUtil.toString(p.getDataAvistamentoAnimal());
        this.pontoReferencia = p.getPontoReferencia();
        this.tipoPedido = p.getTipoPedido();
        this.dataEntradaNoAbrigo = DataUtil.toString(p.getDataEntradaNoAbrigo());
        this.condicoesParaAdocao = p.getCondicoesParaAdocao();
        this.dataInclusao = DataUtil.toString(p.getDataInclusao());
        this.dataAlteracao = DataUtil.toString(p.getDataAlteracao());
    }


    public Pedido transformToObj() {
        Pedido pedido = new Pedido();
        pedido.setPk(this.pk);
        pedido.setDataEntradaNoAbrigo(DataUtil.toLocalDate(this.dataEntradaNoAbrigo));
        pedido.setCondicoesParaAdocao(this.condicoesParaAdocao);
        pedido.setUsuario(this.usuario.transformToObj());
        pedido.setPonto(this.ponto.transformToObj());
        pedido.setAnimal(animal.transformToObj());
        pedido.setDataInclusao(DataUtil.toLocalDateTimeBr(dataInclusao));
        pedido.setDataAlteracao(DataUtil.toLocalDateTimeBr(dataAlteracao));
        pedido.setDataAvistamentoAnimal(DataUtil.toLocalDate(dataAvistamentoAnimal));
        pedido.setPontoReferencia(pontoReferencia);
        pedido.setDescricao(descricao);
        pedido.setTipoPedido(tipoPedido);
        return pedido;
    }
    public static List<PedidoDTO> converter(List<Pedido> list) {
        return list.stream().map(PedidoDTO::new).collect(Collectors.toList());
    }
}
