package lrsystemsweb.com.br.savepetapp.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.core.util.DataUtil;
import lrsystemsweb.com.br.savepetapp.domain.animal.model.Raca;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RacaDTO {

    private Long pk;
    private String nome;
    private String origem;
    private String dataInclusao;
    private String dataAlteracao;

    public RacaDTO(Raca raca) {
        this.pk = raca.getPk();
        this.nome = raca.getNome();
        this.origem = raca.getOrigem();
        this.dataInclusao = DataUtil.toString(raca.getDataInclusao());
        this.dataAlteracao = DataUtil.toString(raca.getDataAlteracao());
    }

    public Raca transformToObj() {
        Raca raca = new Raca();
        raca.setPk(pk);
        raca.setNome(nome);
        raca.setOrigem(origem);
        raca.setDataAlteracao(DataUtil.toLocalDateTimeBr(dataAlteracao));
        raca.setDataInclusao(DataUtil.toLocalDateTimeBr(dataInclusao));
        return raca;
    }

    public static List<RacaDTO> converter(List<Raca> list) {
        return list.stream().map(RacaDTO::new).collect(Collectors.toList());
    }

}
