package lrsystemsweb.com.br.savepetapp.domain.seguranca.social;

import lrsystemsweb.com.br.savepetapp.domain.dto.UsuarioDTO;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.login.UsuarioService;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import static lrsystemsweb.com.br.savepetapp.domain.seguranca.social.FacebookUtils.*;

@Service
public class FacebookServiceImpl implements FacebookService {

    @Autowired
    private UsuarioService usuarioService;

    @Value("${spring.social.facebook.app-id}")
    private String id;

    @Value("${spring.social.facebook.app-secret}")
    private String secret;

    @Value("${app.url-servidor}")
    private String url;


    private FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(id, secret);
    }

    @Override
    public String genereteFacebookAuthorizeUrl() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(getUrlFacebook());
        parameters.setScope("email");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    public String getUrlFacebook() {
        return url.concat("/sign").concat("/facebook");
    }

    @Override
    public TokenDto logarComToken(String code) {
        String acessToken =  createConnection().getOAuthOperations()
                .exchangeForAccess(code, getUrlFacebook(), null).getAccessToken();
        Facebook facebook = new FacebookTemplate(acessToken);

        User facebookUser = facebook.fetchObject(LOGGED_USER_ID, User.class, TODOS);

        return usuarioService.criaLogin(new UsuarioDTO(facebookUser));
    }


}
