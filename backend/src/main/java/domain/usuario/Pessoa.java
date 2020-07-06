package domain.usuario;

import domain.comun.Sexo;
import org.joda.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class Pessoa {

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private String bio;
}
