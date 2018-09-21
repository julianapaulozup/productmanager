package productManager.product.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import productManager.product.ErrorDetails;
import productManager.product.exception.ProductNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourseExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundException
            (ProductNotFoundException e, HttpServletRequest request){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404l);
        error.setTimestamp(System.currentTimeMillis());
        error.setTitle("Produto não pode ser encontrado");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
