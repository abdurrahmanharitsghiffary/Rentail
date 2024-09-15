package com.abdhage.rentail.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    @Value("${server.servlet.context-path}")
    public String BASE_URL;
    public String[] WHITE_LIST = {"/auth/**"};
    public String[] BLACK_LIST = {"/users/**", "/kos/**", "/me/**"};
}
