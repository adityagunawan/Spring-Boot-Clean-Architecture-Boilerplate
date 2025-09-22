package com.salt.boilerplate.api.config.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GeneralResponse<T> {
    private Boolean isSuccess = true;
    private String message;
    private T data;

    public GeneralResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public GeneralResponse(Boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public static <T> GeneralResponse<T> Ok() {
        return new GeneralResponse<>("Success", null);
    }

    public static <T> GeneralResponse<T> Ok(T data) {
        return new GeneralResponse<>("Success", data);
    }
}
