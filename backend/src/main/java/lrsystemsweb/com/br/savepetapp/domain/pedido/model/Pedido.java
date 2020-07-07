package lrsystemsweb.com.br.savepetapp.domain.pedido.model;

import lrsystemsweb.com.br.savepetapp.domain.animal.model.Animal;
import lrsystemsweb.com.br.savepetapp.domain.comun.ObjetoPersistivel;

import javax.persistence.OneToOne;

public class Pedido extends ObjetoPersistivel {

    @OneToOne
    private Animal animal;
}
