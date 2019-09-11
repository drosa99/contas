package br.com.contas.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UnauthorizedException extends HttpException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(SimpleError error) {
        super(error);
    }

    public UnauthorizedException(Throwable cause, SimpleError error) {
        super(cause, error);
    }

    public UnauthorizedException(String message, Map<String, String>... args) {
        super(message, args);
    }

    public UnauthorizedException(String code, String message, Map<String, String> args) {
        super(code, message, args);
    }

    public UnauthorizedException(Throwable cause, String message, Map<String, String> args) {
        super(cause, message, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
