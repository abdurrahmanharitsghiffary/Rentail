package com.abdhage.rentail.common.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Util {
    public String genInvoiceId() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return "INV/" + cal.getTimeInMillis() + "/RNL/" + year;
    }
}
