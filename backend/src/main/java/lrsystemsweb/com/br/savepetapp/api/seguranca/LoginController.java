package lrsystemsweb.com.br.savepetapp.api.seguranca;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lrsystemsweb.com.br.savepetapp.domain.dto.UsuarioDTO;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.TokenDto;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.login.UsuarioService;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.social.FacebookService;
import lrsystemsweb.com.br.savepetapp.domain.seguranca.social.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/sign")
@Api(value = "Api REST Segurança")
public class LoginController {

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping(path = "/facebook/genereteFacebookAuthorizeUrl")
    @ApiOperation(value = "Gera Facebook Url de Login")
    public String genereteFacebookAuthorizeUrl() {
        return this.facebookService.genereteFacebookAuthorizeUrl();
    }

    @GetMapping(path = "/facebook", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gera Facebook Url de Login com Token Aberto")
    public ResponseEntity<TokenDto> logaComFacebook(@RequestParam("code") String code) {
        TokenDto token = this.facebookService.logarComToken(code);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/google/genereteGoogleAuthorizeUrl")
    public String genereteGoogleAuthorizeUrl() {
        return this.googleService.genereteGoogleAuthorizeUrl();
    }

    @GetMapping(path = "/google")
    public ResponseEntity<TokenDto> logaComGoogle(@RequestParam("code") String code) {
        TokenDto token = this.googleService.logarComToken(code);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/criaLogin")
    @ApiOperation(value = "Cria Usuario")
    public ResponseEntity<TokenDto> singup(@RequestBody UsuarioDTO userForm){
        TokenDto token = this.usuarioService.criaLogin(userForm);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


    @GetMapping
    @ApiOperation(value = "Pega Usuario por Id")
    public UsuarioDTO get(
            @RequestParam(value = "idUser", required = true, defaultValue = "") Long idUser){
        return usuarioService.getUsuario(idUser);
    }

    @PostMapping(path = "/logar")
    @ApiOperation(value = "Loga Usuario sem Rede Social")
    public ResponseEntity<TokenDto> logar(@RequestBody @Valid UsuarioDTO form){
        TokenDto token = this.usuarioService.logar(form);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
