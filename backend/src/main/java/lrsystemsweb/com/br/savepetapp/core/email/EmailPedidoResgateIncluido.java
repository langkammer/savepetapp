package lrsystemsweb.com.br.savepetapp.core.email;

import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import org.springframework.stereotype.Service;

@Service
public class EmailPedidoResgateIncluido extends EmailBase {

    private String assunto = "PEDIDO RESGATE";
    private StringBuilder msg = new StringBuilder()
            .append(" Ola seu Pedido de Resgate foi incluido com sucesso !");

    public void notificar(Pedido pedido) {
        setContent(msg.toString());
        setEmails(new String[] {pedido.getUsuario().getPessoa().getEmail()});
        setFrom(assunto);
        enviar();
    }
}
