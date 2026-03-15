package com.zanosov.infrastructure.web.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Class<?> objectType, Object id) {
        super("%s with id '%s' not found".formatted(objectType.getSimpleName(), id));
    }
}
