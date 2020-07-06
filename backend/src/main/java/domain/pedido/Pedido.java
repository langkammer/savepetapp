package domain.pedido;

import domain.comun.ObjetoPersistivel;

import javax.persistence.OneToOne;

public class Pedido extends ObjetoPersistivel {

    @OneToOne
    private Animal animal;
}
