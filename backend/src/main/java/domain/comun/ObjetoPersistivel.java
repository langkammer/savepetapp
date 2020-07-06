package domain.comun;


import domain.usuario.Usuario;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "OBJ_PERSIT")
public class ObjetoPersistivel {

    @Id
    private Long pk;

    @Column
    private LocalDateTime dataInclusao;

    @Column
    private LocalDateTime dataAlteracao;

    @OneToOne
    private Usuario usuario;
}
