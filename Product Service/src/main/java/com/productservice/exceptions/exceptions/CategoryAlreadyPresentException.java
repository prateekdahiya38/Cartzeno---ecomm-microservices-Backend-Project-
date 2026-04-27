package com.productservice.exceptions.exceptions;

public class CategoryAlreadyPresentException extends RuntimeException {
    public CategoryAlreadyPresentException() {
    }

    public CategoryAlreadyPresentException(String message) {
        super(message);
    }

    public CategoryAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public CategoryAlreadyPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
