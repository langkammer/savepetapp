package lrsystemsweb.com.br.savepetapp.domain.seguranca.login;

import lrsystemsweb.com.br.savepetapp.core.email.EmailCadastroSistema;
import lrsystemsweb.com.br.savepetapp.domain.dto.UsuarioDTO;
import lrsystemsweb.com.br.savepetapp.domain.pedido.exceptions.NegocioException;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.repository.RoleRepository;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.repository.UsuarioRepository;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static lrsystemsweb.com.br.savepetapp.domain.seguranca.Role.USUARIO_COMUN;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService token;
    @Autowired
    private EmailCadastroSistema emailCadastroSistema;

    private LocalDateTime dataAtual = new LocalDateTime();

    @Transactional
    public TokenDto criaLogin(UsuarioDTO userForm) {
        try{
            if(userForm != null){
                criaUsuario(userForm);
                Authentication authentication = autentica(userForm);
                return new TokenDto(this.token.gerarToken(authentication),"Bearer");
            }
        }
        catch (AuthenticationException e){
            return null;
        }
        return null;
    }

    private Authentication autentica(UsuarioDTO userForm) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userForm.getPessoa().getEmail(), gerarSenhaNoEncode(userForm));
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    private Usuario criaUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByPessoaEmail(usuarioDTO.getPessoa().getEmail());
        if (!usuarioOptional.isPresent()) {
            Usuario usuario = usuarioDTO.transformToObj();
            String senha = gerarSenhaEncode(usuarioDTO);
            usuario.setPassword(senha);
            usuario.setPerfil(singletonList(roleRepository.findByRole(USUARIO_COMUN)));
            usuario.setDataAlteracao(dataAtual);
            usuario.setDataInclusao(dataAtual);
            emailCadastroSistema.notificar(usuario);
            return (Usuario) usuarioRepository.save(usuario);
        }
        return usuarioOptional.get();
    }

    private String gerarSenhaNoEncode(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getTipoLogin().isLoginSocial() ? "N/A" :
                usuarioDTO.getPassword();
    }

    private String gerarSenhaEncode(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getTipoLogin().isLoginSocial() ? getEncode("N/A") :
                getEncode(usuarioDTO.getPassword());
    }

    private String getEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public UsuarioDTO getUsuario(Long idUser) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUser);
        return usuarioOptional.isPresent() ? new UsuarioDTO(usuarioOptional.get()) : null;
    }

    public TokenDto logar(UsuarioDTO form) {
        try{
            Optional<Usuario> usuarioOptional = usuarioRepository.findByPessoaEmail(form.getPessoa().getEmail());
            if (!usuarioOptional.get().getTipoLogin().isLoginSocial()) {
                Authentication authentication = autentica(form);
                String token = this.token.gerarToken(authentication);
                return new TokenDto(token,"Bearer");
            } else {
                throw new NegocioException("Login Associado Conta em Rede Social favor efetuar login pelo botão do : "
                        + usuarioOptional.get().getTipoLogin().toString());
            }

        }
        catch (AuthenticationException e){
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> u = usuarioRepository.findByPessoaEmail(s);

        if(u.isPresent()) {
            return u.get();
        }

        throw  new UsernameNotFoundException("Usuario ou senha não Localizado!");
    }

    public Usuario getUsuarioLogado() {
        return (Usuario) usuarioRepository.findById(token.getIdUsuarioLogado()).get();
    }

    public Usuario getUsuarioById(Long id) {
        return (Usuario) usuarioRepository.findById(id).get();
    }
}
