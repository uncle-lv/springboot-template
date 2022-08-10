package com.example.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse<T> implements Serializable {
    private static final long serialVersionUID = 4822204564104716702L;

    private Integer code;
    private String msg;
    private T data;

    public static <T> JsonResponse<T> OK() {
        return new JsonResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                null
        );
    }

    public static <T> JsonResponse<T> OK(T data) {
        return new JsonResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                data
        );
    }
}
