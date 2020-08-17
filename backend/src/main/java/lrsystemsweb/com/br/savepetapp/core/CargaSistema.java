package lrsystemsweb.com.br.savepetapp.core;

import lrsystemsweb.com.br.savepetapp.domain.seguranca.Role;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TipoLogin;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.repository.RoleRepository;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.repository.UsuarioRepository;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Pessoa;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Roles;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.apache.tomcat.jni.Local;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.singletonList;


@Service
public class CargaSistema {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void cargaSistema() {
        criaRoles();
        criaUsuarioRobson();
    }

    private void criaUsuarioRobson() {
        Usuario usuario = new Usuario();
        usuario.setPerfil(singletonList(roleRepository.findByRole(Role.USUARIO_ADMINISTRADOR)));
        usuario.setDataInclusao(new LocalDateTime());
        usuario.setDataAlteracao(new LocalDateTime());
        usuario.setPassword(new BCryptPasswordEncoder().encode("123456"));
        usuario.setTipoLogin(TipoLogin.APP);
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Robson Emilio Langkammer");
        pessoa.setEmail("robsonlangkammer@hotmail.com");
        usuario.setPessoa(new Pessoa());
    }

    private void criaRoles() {
        for (Role role : Role.values()) {
            Roles roles = new Roles();
            roles.setRole(role);
            roleRepository.save(roles);
        }
    }
}
