package lrsystemsweb.com.br.savepetapp.domain.comun;

public enum TipoPedido {
    ADOCAO(0, "Adocao"),
    RESGATE(1, "Resgate"),
    TODOS(2, "Todos");
    public static final String adocao = ADOCAO.toString();
    public static final String resgate = RESGATE.toString();
    private String nome;
    private Integer cod;

    TipoPedido(Integer cod, String nome){
        this.cod = cod;
        this.nome = nome;
    }
    public String toString() {
        return this.nome;
    }
}
