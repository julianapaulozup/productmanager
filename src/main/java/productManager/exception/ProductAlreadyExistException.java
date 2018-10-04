package productManager.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String mensagem) {
        super(mensagem);
    }

    public ProductAlreadyExistException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}


