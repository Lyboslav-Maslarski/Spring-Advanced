package com.example.errorhandling.model;

public class ApiErrorDTO {

    private Long objectId;
    private String message;

    public ApiErrorDTO() {
    }

    public Long getObjectId() {
        return objectId;
    }

    public ApiErrorDTO setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
