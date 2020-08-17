package lrsystemsweb.com.br.savepetapp.core.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void send(SimpleMailMessage simpleMailMessage);

}
