package com.abdhage.rentail.exception;


import com.abdhage.rentail.exception.common.BadRequestException;

public class KosNameMustUniqueException extends BadRequestException {
    public KosNameMustUniqueException() {
        super("Kos name already taken");
    }
}
