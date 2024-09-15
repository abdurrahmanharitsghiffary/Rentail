package com.goshier.kopos.exception;


import com.goshier.kopos.exception.common.BadRequestException;

public class KosNameMustUniqueException extends BadRequestException {
    public KosNameMustUniqueException() {
        super("Kos name already taken");
    }
}
