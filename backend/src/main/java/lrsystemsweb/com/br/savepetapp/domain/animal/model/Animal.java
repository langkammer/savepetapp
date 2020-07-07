package lrsystemsweb.com.br.savepetapp.domain.animal.model;

import lombok.Getter;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.animal.enums.StatusAnimal;
import lrsystemsweb.com.br.savepetapp.domain.comun.ObjetoPersistivel;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Animal extends ObjetoPersistivel {

    @Column
    @Getter @Setter
    private String nome;

    @OneToMany
    @Getter @Setter
    @NotBlank(message = "Email is mandatory")
    private Raca raca;

    @Column
    @Getter @Setter
    @NotBlank(message = "Email is mandatory")
    private LocalDate dataVizualizacao;

    @Column
    @Getter @Setter
    private StatusAnimal statusAnimal;

}
