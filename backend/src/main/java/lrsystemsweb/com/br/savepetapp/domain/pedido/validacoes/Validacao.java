package lrsystemsweb.com.br.savepetapp.domain.pedido.validacoes;

import lrsystemsweb.com.br.savepetapp.domain.pedido.exceptions.NegocioException;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

public abstract class Validacao {
    private List<String> msgsErros = newArrayList();
    public void incluirErro(String erro) {
        msgsErros.add(erro);
    }
    public void lancaExcecaoSeHouver() {
        if (!msgsErros.isEmpty()) {
            throw new NegocioException(msgsErros.stream().collect(joining()));
        }
    }

}
