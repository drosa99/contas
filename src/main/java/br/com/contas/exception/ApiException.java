package br.com.contas.exception;


import java.util.Map;

public abstract class ApiException extends RuntimeException {
    protected SimpleError error;
    protected Throwable cause;

    private static final String DEFAULT_MESSAGE = "error.500";

    private ApiException(String message) {
        super(message);
    }

    public ApiException() {
        this(new SimpleError(DEFAULT_MESSAGE));
    }

    public ApiException(Throwable cause) {
        this(new SimpleError(DEFAULT_MESSAGE));
        this.cause = cause;
    }

    public ApiException(SimpleError error) {
        this(error.getMessage());
        this.error = error;
    }

    public ApiException(Throwable cause, SimpleError error) {
        this(error);
        this.cause = cause;
    }

    public ApiException(String message, Map<String, String>... args) {
        this(new SimpleError(message));
    }

    public ApiException(String code, String message, Map<String, String> args) {
        this(SimpleErrorBuilder.create(message).code(code).messageArgs(args).build());
    }

    public ApiException(Throwable cause, String message, Map<String, String> args) {
        this(cause, SimpleErrorBuilder.create(message).messageArgs(args).build());
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    public SimpleError getError() {
        return error;
    }
}
