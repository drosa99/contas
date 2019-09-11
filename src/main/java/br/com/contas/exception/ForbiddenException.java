package br.com.contas.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ForbiddenException extends HttpException {

    public ForbiddenException() {
        super("error.403");
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, "error.403", new HashMap<>());
    }

    public ForbiddenException(SimpleError error) {
        super(error);
    }

    public ForbiddenException(Throwable cause, SimpleError error) {
        super(cause, error);
    }

    public ForbiddenException(String message, Map<String, String>... args) {
        super(message, args);
    }

    public ForbiddenException(String code, String message, Map<String, String> args) {
        super(code, message, args);
    }

    public ForbiddenException(Throwable cause, String message, Map<String, String> args) {
        super(cause, message, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
