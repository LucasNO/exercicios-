package com.apiconcessionaria.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1546231799285509900L;

    public BusinessException(final String message) {
        super(message);
    }
}
