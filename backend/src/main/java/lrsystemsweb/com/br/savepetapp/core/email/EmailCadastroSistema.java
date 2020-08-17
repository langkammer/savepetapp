package lrsystemsweb.com.br.savepetapp.core.email;

import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class EmailCadastroSistema extends EmailBase {

    private String assunto = "Bem Vindo";
    private StringBuilder msg = new StringBuilder()
            .append(" Ola seja bem vindo ao Save Pet!");

    public void notificar(Usuario usuario) {
        setContent(msg.toString());
        setEmails(new String[] {usuario.getPessoa().getEmail()});
        setFrom(assunto);
        enviar();
    }
}
