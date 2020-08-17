package lrsystemsweb.com.br.savepetapp.domain.pedido.validacoes;

import lrsystemsweb.com.br.savepetapp.domain.pedido.model.Pedido;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Usuario;

import static com.nimbusds.oauth2.sdk.util.StringUtils.isNotBlank;
import static lrsystemsweb.com.br.savepetapp.domain.comun.TipoPedido.ADOCAO;
import static lrsystemsweb.com.br.savepetapp.domain.comun.TipoPedido.RESGATE;

public class ValidacaoPedidos extends Validacao {

    private Pedido pedido;
    private Usuario usuarioLogado;

    private String MSG_USUARIO_NAO_PODE_ALTERAR_PEDIDO_DE_OUTRO_USUARIO = "O usuario : ${0}, " +
            "não pode alterar o pedido pois ele não tem permissão ou não foi ele que o incluiu";

    private String MSG_CAMPO_OBRIGATORIO = "Preencha o Campo ${0}";


    public void validar() {
        usuarioPodeAlterarPedido();
        devePreencherCamposAdocao();
        devePreencherCamposResgate();
        lancaExcecaoSeHouver();
    }


    public ValidacaoPedidos(Pedido p, Usuario usuarioLogado) {
        this.pedido = p;
        this.usuarioLogado = usuarioLogado;
    }

    public void usuarioPodeAlterarPedido() {
        if (verificaSeUsuarioPodeEditarPedido()) {
            incluirErro(MSG_USUARIO_NAO_PODE_ALTERAR_PEDIDO_DE_OUTRO_USUARIO
                    .format(pedido.getUsuario().getPessoa().getNome()));
        }
    }

    public void devePreencherCamposAdocao() {
        if (this.pedido.getTipoPedido().equals(ADOCAO)) {
            camposObrigatoriosAdocao();
        }
    }

    public void devePreencherCamposResgate() {
        if (this.pedido.getTipoPedido().equals(RESGATE)) {
            camposObrigatoriosResgate();
        }
    }

    private boolean verificaSeUsuarioPodeEditarPedido() {
        return this.pedido != null
            && this.pedido.getPk() != null
            && this.usuarioLogado != null
            && !this.pedido.getUsuario().getPessoa().getEmail().equals(this.usuarioLogado.getPessoa().getEmail())
            && !this.usuarioLogado.ehUsuarioAdministrador();
    }


    private void camposObrigatoriosAdocao() {
        if (isNotBlank(this.pedido.getCondicoesParaAdocao())){
            incluirErro(MSG_CAMPO_OBRIGATORIO
                    .format(" Condições para Adoção"));
        } else if (this.pedido.getDataEntradaNoAbrigo() != null) {
            incluirErro(MSG_CAMPO_OBRIGATORIO
                    .format(" Entrada no Abrigo"));
        }

    }

    private void camposObrigatoriosResgate() {
        if (this.pedido.getDataAvistamentoAnimal() != null) {
            incluirErro(MSG_CAMPO_OBRIGATORIO
                    .format(" Data do Avistamento"));
        }
    }
}
