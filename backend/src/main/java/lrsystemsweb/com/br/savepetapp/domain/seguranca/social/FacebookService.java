package lrsystemsweb.com.br.savepetapp.domain.seguranca.social;

import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;

public interface FacebookService {

    String genereteFacebookAuthorizeUrl();

    TokenDto logarComToken(String code);
}
