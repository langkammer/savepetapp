package lrsystemsweb.com.br.savepetapp.domain.seguranca;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority {
    USUARIO_COMUN(0, "Usuario Comun"),
    USUARIO_MODERADOR(1, "Usuario Moderador de Pedidos"),
    USUARIO_ACOLHEMENTO_ANIMAL(2, "Usuario Acolhemento Animal"),
    USUARIO_ADMINISTRADOR(3, "Adminsitador");

    private String nome;
    private Integer cod;

    Role(Integer cod, String nome){
        this.cod = cod;
        this.nome = nome;
    }
    public String toString() {
        return this.nome;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
