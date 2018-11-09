package productManager_unitTests.exception;

public class EvaluationNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public EvaluationNotFoundException(String mensagem) {
        super(mensagem);
    }

    public EvaluationNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
