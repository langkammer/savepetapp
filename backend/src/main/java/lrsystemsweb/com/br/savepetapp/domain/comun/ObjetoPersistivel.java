package lrsystemsweb.com.br.savepetapp.domain.comun;


import lombok.Getter;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "OBJ_PERSIT")
public class ObjetoPersistivel {

    @Id
    @Getter @Setter
    private Long pk;

    @Column
    @Getter @Setter
    private LocalDateTime dataInclusao;

    @Column
    @Getter @Setter
    private LocalDateTime dataAlteracao;

    @OneToOne
    @Getter @Setter
    private Usuario usuario;
}
