package br.com.contas.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UnexpectedException extends HttpException {

    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

    public UnexpectedException(SimpleError error) {
        super(error);
    }

    public UnexpectedException(Throwable cause, SimpleError error) {
        super(cause, error);
    }

    public UnexpectedException(String message, Map<String, String>... args) {
        super(message, args);
    }

    public UnexpectedException(String code, String message, Map<String, String> args) {
        super(code, message, args);
    }

    public UnexpectedException(Throwable cause, String message, Map<String, String> args) {
        super(cause, message, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
