package com.github.dashframe.service.service;

import java.io.Serial;

public class MissingApiKeyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2540028610181841949L;

    public MissingApiKeyException(String message) {
        super(message);
    }
}
