package lrsystemsweb.com.br.savepetapp.domain.usuario;

import lrsystemsweb.com.br.savepetapp.domain.comun.ObjetoPersistivel;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Usuario extends ObjetoPersistivel {


    @Embedded
    private Pessoa pessoa;
}
