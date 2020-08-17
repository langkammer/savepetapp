package lrsystemsweb.com.br.savepetapp.domain.animal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.animal.enums.StatusAnimal;
import lrsystemsweb.com.br.savepetapp.domain.animal.enums.TipoAnimal;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Animal  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column
    private String nome;

    @OneToOne(cascade=CascadeType.DETACH)
    private Raca raca;

    @Column
    private LocalDate dataVizualizacao;

    @Column
    private StatusAnimal statusAnimal;

    @Column
    private TipoAnimal tipoAnimal;

    @Transient
    private String foto;

    @Column
    private String nomeFoto;

    @Column
    private LocalDateTime dataInclusao;

    @Column
    private LocalDateTime dataAlteracao;

}
