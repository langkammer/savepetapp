package lrsystemsweb.com.br.savepetapp.domain.seguranca.login;

import com.azure.core.http.HttpHeader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class TokenService {
    @Value("${api.jwt.expiration}")
    private String expiration;

    @Value("${api.jwt.secret}")
    private String secret;

    @Autowired
    private HttpServletRequest httpHeader;

    public String gerarToken(Authentication authentication) {
        Usuario login = (Usuario) authentication.getPrincipal();
        LocalDate hoje = new LocalDate();
        LocalDate expiration = new LocalDate(hoje.toDate().getTime() + Long.parseLong(this.expiration));
        return Jwts.builder()
                .setIssuer("Api")
                .setSubject(login.getPk().toString())
                .setIssuedAt(hoje.toDate())
                .setExpiration(expiration.toDate())
                .signWith(SignatureAlgorithm.HS256,this.secret)
                .compact();
    }


    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);

            return  true;
        }
        catch (Exception e ){
            return false;
        }


    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token).getBody();

        return  Long.parseLong(claims.getSubject());
    }

    public Long getIdUsuarioLogado() {
        return  getIdUser(httpHeader.getHeader("Authorization").replaceAll("Bearer", "").trim());
    }
    
}
