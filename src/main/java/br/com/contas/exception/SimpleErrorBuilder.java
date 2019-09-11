package br.com.contas.exception;


import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class SimpleErrorBuilder {
    protected SimpleError error;

    public SimpleErrorBuilder(String message) {
        error = new SimpleError(message);
    }

    public static SimpleErrorBuilder create(String message) {
        return new SimpleErrorBuilder(message);
    }

    public SimpleErrorBuilder code(String code) {
        error.setCode(code);
        return this;
    }

    public SimpleErrorBuilder object(Object object) {
        error.setObject(object);
        return this;
    }

    public SimpleErrorBuilder detail(SimpleError detail) {
        error.addDetail(detail);
        return this;
    }

    public SimpleErrorBuilder messageArgs(String key, String value) {
        error.addMessageArgs(key, value);
        return this;
    }

    public SimpleErrorBuilder messageArgs(Map<String, String> args) {
        error.addMessageArgs(args);
        return this;
    }

    public SimpleErrorBuilder messageArgs(ImmutableMap<String, String> args) {
        error.addMessageArgs(args);
        return this;
    }

    public SimpleError build() {
        return error;
    }
}
