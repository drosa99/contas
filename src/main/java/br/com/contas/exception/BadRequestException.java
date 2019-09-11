package br.com.contas.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BadRequestException extends HttpException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(SimpleError error) {
        super(error);
    }

    public BadRequestException(Throwable cause, SimpleError error) {
        super(cause, error);
    }

    public BadRequestException(String message, Map<String, String>... args) {
        super(message, args);
    }

    public BadRequestException(String code, String message, Map<String, String> args) {
        super(code, message, args);
    }

    public BadRequestException(Throwable cause, String message, Map<String, String> args) {
        super(cause, message, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
