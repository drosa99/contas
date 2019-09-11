package br.com.contas.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ExpectedException extends HttpException {

    public ExpectedException() {
        super();
    }

    public ExpectedException(String message) {
        super(message);
    }

    public ExpectedException(Throwable cause) {
        super(cause);
    }

    public ExpectedException(SimpleError error) {
        super(error);
    }

    public ExpectedException(Throwable cause, SimpleError error) {
        super(cause, error);
    }

    public ExpectedException(String message, Map<String, String>... args) {
        super(message, args);
    }

    public ExpectedException(String code, String message, Map<String, String> args) {
        super(code, message, args);
    }

    public ExpectedException(Throwable cause, String message, Map<String, String> args) {
        super(cause, message, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.EXPECTATION_FAILED;
    }
}
