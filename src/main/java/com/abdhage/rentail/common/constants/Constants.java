package com.abdhage.rentail.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${server.servlet.context-path}")
    public String BASE_URL;
    public String[] WHITE_LIST = {"/auth/**"};
    public String[] BLACK_LIST = {"/users/**", "/kos/**", "/me/**"};
}
