package com.productservice.exceptions.exceptions;

public class FakeStoreException extends Exception {
    public FakeStoreException() {
    }

    public FakeStoreException(String message) {
        super(message);
    }

    public FakeStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public FakeStoreException(Throwable cause) {
        super(cause);
    }

    public FakeStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
