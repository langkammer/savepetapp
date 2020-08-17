package lrsystemsweb.com.br.savepetapp.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lrsystemsweb.com.br.savepetapp.core.util.DataUtil;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TipoLogin;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Pessoa;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import lrsystemsweb.com.br.savepetapp.domain.usuario.enums.TipoUsuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.plus.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO  {

    private Long pk;
    private TipoUsuario tipoUsuario;
    private Pessoa pessoa;
    private String dataInclusao;
    private String dataAlteracao;
    private TipoLogin tipoLogin;
    private String password;

    public UsuarioDTO(Person person) {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(person.getAccountEmail());
        pessoa.setFoto(person.getImageUrl());
        pessoa.setNome(person.getDisplayName());
        this.pessoa = pessoa;
        this.tipoLogin = TipoLogin.GOOGLE;
    }

    public UsuarioDTO(Usuario usuario) {
        this.pk = usuario.getPk();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.pessoa = usuario.getPessoa();
        this.password = new BCryptPasswordEncoder().encode(usuario.getPassword());
        this.dataAlteracao = DataUtil.toString(usuario.getDataAlteracao());
        this.dataInclusao = DataUtil.toString(usuario.getDataInclusao());
        this.tipoLogin = usuario.getTipoLogin();
    }

    public UsuarioDTO(User usuario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(usuario.getEmail());
        pessoa.setFoto(getPathFoto(usuario.getExtraData()));
        pessoa.setNome(usuario.getName());
        this.pessoa = pessoa;
        this.tipoLogin = TipoLogin.FACEBOOK;
    }

    public String getPathFoto(Map<String, Object> hashMap) {
        if(hashMap.containsKey("picture")) {
            Map<String, Object> pictureObj = (Map<String, Object>) hashMap.get("picture");
            if(pictureObj.containsKey("data")) {
                Map<String, Object>  dataObj = (Map<String, Object>) pictureObj.get("data");
                if(dataObj.containsKey("url")) {
                    return (String) dataObj.get("url");
                }
            }
        }
        return null;
    }


    public Usuario transformToObj() {
        Usuario usuario = new Usuario();
        usuario.setPessoa(getPessoa());
        usuario.setPk(getPk());
        usuario.setTipoUsuario(getTipoUsuario());
        usuario.setDataAlteracao(DataUtil.toLocalDateTimeBr(getDataAlteracao()));
        usuario.setDataInclusao(DataUtil.toLocalDateTimeBr(getDataInclusao()));
        usuario.setTipoLogin(getTipoLogin());
        usuario.setPassword(getPassword());
        return usuario;
    }


    public static List<UsuarioDTO> converter(List<Usuario> list) {
        return list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }



}
