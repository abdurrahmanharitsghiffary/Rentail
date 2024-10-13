package com.abdhage.rentail.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${server.servlet.context-path}")
    public String BASE_URL;
    public String[] WHITE_LIST = {"/auth/**"};
    public String[] BLACK_LIST = {"/users/**", "/accommodations/**", "/me/**"};
    public double Y_IN_MS = 1000 * 60 * 60 * 24 * 30.33 * 12;
    public double MONTH_IN_MS = 1000 * 60 * 60 * 24 * 30.33;
    public double DAY_IN_MS = 1000 * 60 * 60 * 24;
    public double H_IN_MS = 1000 * 60 * 60;
    public double WEEK_IN_MS = 1000 * 60 * 60 * 24 * 7;

}
