package com.example.todo.TodoApp.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;
    String fieldValue2;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
