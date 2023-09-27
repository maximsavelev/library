package ru.savelyev.library.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchResourceException extends RuntimeException {
    public NoSuchResourceException(String message) {
        super(message);
    }
}
