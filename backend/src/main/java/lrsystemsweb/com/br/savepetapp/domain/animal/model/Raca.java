package lrsystemsweb.com.br.savepetapp.domain.animal.model;

import lombok.Getter;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.comun.ObjetoPersistivel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Raca extends ObjetoPersistivel {

    @Column
    @Getter @Setter
    private String nome;

    @Column
    @Getter @Setter
    private String origem;
}
