package com.abdhage.rentail.common.response;

import lombok.EqualsAndHashCode;

import javax.lang.model.type.NullType;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ApiErrorResponse<E> extends ApiResponse<NullType> {
    private final String name;
    private final List<E> errors;

    public ApiErrorResponse(String message, Integer status, String name, List<E> errors) {
        super(null, status, message);

        this.name = name;
        this.errors = errors;
    }
}
