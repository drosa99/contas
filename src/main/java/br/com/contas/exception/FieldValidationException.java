package br.com.contas.exception;

import org.springframework.http.HttpStatus;

public class FieldValidationException extends HttpException {

    public FieldValidationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
