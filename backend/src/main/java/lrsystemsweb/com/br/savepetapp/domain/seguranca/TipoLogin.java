package lrsystemsweb.com.br.savepetapp.domain.seguranca;

public enum TipoLogin {
    APP(0, "App"),
    FACEBOOK(1, "Facebook"),
    GOOGLE(2, "Google");

    private String nome;
    private Integer cod;

    TipoLogin(Integer cod, String nome){
        this.cod = cod;
        this.nome = nome;
    }
    public String toString() {
        return this.nome;
    }

    public boolean isLoginSocial() {
        return !this.equals(APP);
    }
}
