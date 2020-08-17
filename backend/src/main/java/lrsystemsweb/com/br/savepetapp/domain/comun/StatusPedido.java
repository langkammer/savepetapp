package lrsystemsweb.com.br.savepetapp.domain.comun;

public enum StatusPedido {
    APROVADO(0, "Aprovado"),
    PENDENTE(1, "Pendente"),
    TODOS(2, "Todos");
    public static final String aprovado = APROVADO.toString();
    public static final String pendente = PENDENTE.toString();
    private String nome;
    private Integer cod;

    StatusPedido(Integer cod, String nome){
        this.cod = cod;
        this.nome = nome;
    }
    public String toString() {
        return this.nome;
    }
}
