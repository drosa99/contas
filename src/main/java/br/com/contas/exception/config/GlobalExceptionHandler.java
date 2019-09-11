package br.com.contas.exception.config;

import br.com.contas.exception.HttpException;
import br.com.contas.exception.SimpleError;
import br.com.contas.exception.SimpleErrorTranslator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleErrorTranslator errorTranslator;

    @ExceptionHandler
    public ResponseEntity<SimpleError> handleException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException e = (HttpException) throwable;
            SimpleError error = translate(e.getError());
            this.log.error(error.getMessage(), e.getCause() != null ? e.getCause() : e);
            return ResponseEntity.status(e.getHttpStatus()).body(error);
        }
        this.log.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @ExceptionHandler
    public ResponseEntity<SimpleError> handlerServerInputException(ServerWebInputException e) {
        this.log.error(e.getMessage(), e);
        SimpleError error = new SimpleError();
        error.setMessage("error.missingServletRequestParameter");
        error.addDetail(e.getMessage());
        return ResponseEntity.badRequest().body(translate(error));
    }

    @ExceptionHandler
    public ResponseEntity<SimpleError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        this.log.error(e.getMessage(), e);
        SimpleError error = new SimpleError();
        error.setMessage("error.methodArgumentTypeMismatch");
        error.addDetail(String.format("Argument '%s' should be a valid '%s' but it is '%s'.", e.getName(), e.getRequiredType().getSimpleName(), e.getValue()));
        return new ResponseEntity<>(translate(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SimpleError> handleJsonProcessingException(JsonProcessingException e) {
        this.log.error(e.getMessage(), e);
        SimpleError error = new SimpleError();
        error.setMessage("error.jsonProcessing");
        error.addDetail(e.getMessage());
        return new ResponseEntity<>(translate(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SimpleError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        this.log.error(e.getMessage(), e);
        SimpleError error = new SimpleError();
        error.setMessage("error.argumentNotValid");

        if (e.getBindingResult() != null && e.getBindingResult().getAllErrors() != null) {
            e.getBindingResult().getAllErrors().forEach(erro -> error.addDetail(erro.getDefaultMessage()));
        } else {
            error.addDetail(e.getMessage());
        }
        return new ResponseEntity<>(translate(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SimpleError> handleConstraintViolationException(ConstraintViolationException e) {
        this.log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .map(x -> x.getMessage())
                .findFirst()
                .get();
        SimpleError error = new SimpleError(message);
        error.addDetail(e.getMessage());
        return new ResponseEntity<>(translate(error), HttpStatus.BAD_REQUEST);
    }

    private SimpleError translate(SimpleError error) {
        return errorTranslator.translate(error);
    }
}
