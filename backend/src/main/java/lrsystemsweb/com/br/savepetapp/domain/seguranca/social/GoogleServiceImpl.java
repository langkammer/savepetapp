package lrsystemsweb.com.br.savepetapp.domain.seguranca.social;

import lrsystemsweb.com.br.savepetapp.domain.dto.UsuarioDTO;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.login.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Autowired
    private UsuarioService usuarioService;

    @Value("${spring.social.google.app-id}")
    private String id;

    @Value("${spring.social.google.app-secret}")
    private String secret;

    @Value("${app.url-servidor}")
    private String url;

    private GoogleConnectionFactory createConnection() {
        return new GoogleConnectionFactory(id, secret);
    }

    @Override
    public String genereteGoogleAuthorizeUrl() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(getUrlGoogle());
        parameters.setScope("https://www.googleapis.com/auth/plus.login");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    public String getUrlGoogle() {
        return url.concat("/sign").concat("/google");
    }

    @Override
    public TokenDto logarComToken(String code) {
         createConnection().getOAuthOperations()
                .exchangeForAccess(code, getUrlGoogle(), null).getAccessToken();
        Google google = new GoogleTemplate(code);

        Person person = google.plusOperations().getGoogleProfile();
        return usuarioService.criaLogin(new UsuarioDTO(person));
    }

//    {
//        "client_id": "764086051850-6qr4p6gpi6hn506pt8ejuq83di341hur.apps.googleusercontent.com",
//            "client_secret": "d-FL95Q19q7MQmFpd7hHD0Ty",
//            "quota_project_id": "rateshareteste",
//            "refresh_token": "1//053cmsw3n8ARuCgYIARAAGAUSNwF-L9IrxfEAd04b4B8z7ETT_R5Du2a0UnP2czTQJX6WX8oUdoyKz7ZXRgdk8oJDmNsU5gC2_kY",
//            "type": "authorized_user"
//    }
}
