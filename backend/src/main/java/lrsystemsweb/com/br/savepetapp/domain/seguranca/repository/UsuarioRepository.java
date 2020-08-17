package lrsystemsweb.com.br.savepetapp.domain.seguranca.repository;

import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface UsuarioRepository<T, ID extends Serializable>  extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByPessoaEmail(String email);
}
