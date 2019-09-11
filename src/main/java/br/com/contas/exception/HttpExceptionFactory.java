package br.com.contas.exception;

import org.springframework.http.HttpStatus;

public class HttpExceptionFactory {
    public static HttpException get(HttpStatus status, SimpleError error) {
        return get(status.value(), error);
    }

    public static HttpException get(Integer status, SimpleError error) {
        if (status == HttpStatus.BAD_REQUEST.value()) {
            return (error == null) ? new BadRequestException() : new BadRequestException(error);
        } else if (status == HttpStatus.UNAUTHORIZED.value()) {
            return (error == null) ? new UnauthorizedException() : new UnauthorizedException(error);
        } else if (status == HttpStatus.FORBIDDEN.value()) {
            return (error == null) ? new ForbiddenException() : new ForbiddenException(error);
        } else if (status == HttpStatus.EXPECTATION_FAILED.value()) {
            return (error == null) ? new ExpectedException() : new ExpectedException(error);
        }
        return (error == null) ? new UnexpectedException() : new UnexpectedException(error);
    }

    public static HttpException get(Class<? extends HttpException> exceptionClass, SimpleError error) {
        if (exceptionClass == BadRequestException.class) {
            return (error == null) ? new BadRequestException() : new BadRequestException(error);
        } else if (exceptionClass == UnauthorizedException.class) {
            return (error == null) ? new UnauthorizedException() : new UnauthorizedException(error);
        } else if (exceptionClass == ForbiddenException.class) {
            return (error == null) ? new ForbiddenException() : new ForbiddenException(error);
        } else if (exceptionClass == ExpectedException.class) {
            return (error == null) ? new ExpectedException() : new ExpectedException(error);
        }
        return (error == null) ? new UnexpectedException() : new UnexpectedException(error);
    }
}
