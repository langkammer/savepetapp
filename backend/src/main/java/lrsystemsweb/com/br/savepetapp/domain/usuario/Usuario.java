package lrsystemsweb.com.br.savepetapp.domain.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.domain.dto.UsuarioDTO;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TipoLogin;
import lrsystemsweb.com.br.savepetapp.domain.usuario.enums.TipoUsuario;
import org.joda.time.LocalDateTime;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static lrsystemsweb.com.br.savepetapp.domain.seguranca.Role.USUARIO_ADMINISTRADOR;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails , Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column
    private TipoUsuario tipoUsuario;

    @Embedded
    private Pessoa pessoa;

    @Column
    private LocalDateTime dataInclusao;

    @Column
    private LocalDateTime dataAlteracao;

    @Column
    private TipoLogin tipoLogin;

    @Column
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> perfil = new ArrayList<>();

    @Transient
    public boolean ehUsuarioAdministrador() {
       return this.perfil.contains(USUARIO_ADMINISTRADOR);
    }

    public UsuarioDTO transformaDTO() {
        return new UsuarioDTO(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfil;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getPessoa().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Transient
    public UsernamePasswordAuthenticationToken convert() {
        if (this.tipoLogin.isLoginSocial()) {
            return new UsernamePasswordAuthenticationToken(getPessoa().getEmail(), null);
        } else {
            return new UsernamePasswordAuthenticationToken(getPessoa().getEmail(), this.password);
        }
    }
}
