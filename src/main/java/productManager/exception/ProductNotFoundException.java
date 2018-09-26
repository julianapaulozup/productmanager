package productManager.exception;

public class ProductNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public ProductNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ProductNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
