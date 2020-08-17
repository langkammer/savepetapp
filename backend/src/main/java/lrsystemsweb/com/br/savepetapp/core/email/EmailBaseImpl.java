package lrsystemsweb.com.br.savepetapp.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailBaseImpl implements EmailService {


    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void send(SimpleMailMessage simpleMailMessage) {
        emailSender.send(simpleMailMessage);
    }

}
