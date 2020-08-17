package lrsystemsweb.com.br.savepetapp.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

public abstract class EmailBase {

    @Autowired
    private EmailService service;

    private String[] emails;
    private String subjetct;
    private String from;
    private String content;

    protected void enviar() {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom(from);
        simpleMail.setTo(emails);
        simpleMail.setText(content);
    }

    public void setService(EmailService service) {
        this.service = service;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public void setSubjetct(String subjetct) {
        this.subjetct = subjetct;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
