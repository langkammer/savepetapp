package lrsystemsweb.com.br.savepetapp.domain.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column
    private Role role;


    @Override
    public String getAuthority() {
        return role.toString();
    }
}
