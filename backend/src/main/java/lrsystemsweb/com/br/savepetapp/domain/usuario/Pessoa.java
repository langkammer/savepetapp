package lrsystemsweb.com.br.savepetapp.domain.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Pessoa {

    @Column
    @NotEmpty
    private String nome;

    @Column
    @NotEmpty
    private String email;

    @Transient
    @JsonIgnore
    private String foto;

    @Column
    private String nomeFoto;

}
