package lrsystemsweb.com.br.savepetapp.domain.animal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column
    private String nome;

    @Column
    private String origem;

    @Column
    private LocalDateTime dataInclusao;

    @Column
    private LocalDateTime dataAlteracao;
}
