package productManager.exception;

public class PurchaseNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public PurchaseNotFoundException() {
        super();
    }
    public PurchaseNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PurchaseNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

