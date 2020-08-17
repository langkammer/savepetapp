package lrsystemsweb.com.br.savepetapp.core.email;

import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import org.springframework.stereotype.Service;

@Service
public class EmailPedidoAdocaoIncluido extends EmailBase {

    private String assunto = "PEDIDO ADOCAO";
    private StringBuilder msg = new StringBuilder()
            .append(" Ola sua Adocao foi incluido com sucesso aguarde contato dos interessados !");

    public void notificar(Pedido pedido) {
        setContent(msg.toString());
        setEmails(new String[] {pedido.getUsuario().getPessoa().getEmail()});
        setFrom(assunto);
        enviar();
    }
}
