package domain.usuario;

import domain.comun.ObjetoPersistivel;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Usuario extends ObjetoPersistivel {


    @Embedded
    private Pessoa pessoa;
}
