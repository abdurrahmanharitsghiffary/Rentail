package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.NotFoundException;

public class KosNotFoundException extends NotFoundException {
    public KosNotFoundException() {
        super("Kos not found.");
    }
}
