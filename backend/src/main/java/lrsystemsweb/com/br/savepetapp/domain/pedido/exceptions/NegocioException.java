package lrsystemsweb.com.br.savepetapp.domain.pedido.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NegocioException(String msg) {
        super(msg);
    }
}
