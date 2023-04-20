package br.com.caio.apifintrackr.domain.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExpenseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
       String errorMessage =  ex.getBindingResult().getFieldError().getDefaultMessage();
       ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST,errorMessage);
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Object>  expenseNotFound(ExpenseNotFoundException ex){
        ErrorMessage errorMsg = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorMsg,errorMsg.getStatus());
    }
}
