package lrsystemsweb.com.br.savepetapp.domain.seguranca.social;

import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;

public interface GoogleService {

    String genereteGoogleAuthorizeUrl();

    TokenDto logarComToken(String code);
}
