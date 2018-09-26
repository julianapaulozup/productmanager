package productManager.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import productManager.exception.ProductAlreadyExistException;
import productManager.exception.ProductNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourseExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleProductNotFoundException
            (ProductNotFoundException e, HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404l);
        error.setTimestamp(System.currentTimeMillis());
        error.setTitle("Produto não pode ser encontrado");

        return error;
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleProductNotFoundException
            (ProductAlreadyExistException e, HttpServletRequest request){

        ErrorDetails error = new ErrorDetails();
        error.setStatus(400l);
        error.setTimestamp(System.currentTimeMillis());
        error.setTitle("Produto já existente");

        return error;
    }


}
