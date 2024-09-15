package com.abdhage.rentail.common.config;

import com.abdhage.rentail.common.response.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URI;
import java.util.LinkedHashMap;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println(body + " Body Object");
        System.out.println(body.getClass() + " The class");

        if (body instanceof LinkedHashMap<?, ?>) {
            if (((LinkedHashMap<?, ?>) body).containsKey("error") && ((LinkedHashMap<?, ?>) body).containsKey("timestamp")) {
                ProblemDetail problemDetail = null;
                final Integer statusCode = (Integer) ((LinkedHashMap<?, ?>) body).get("status");
                final String message = (String) ((LinkedHashMap<?, ?>) body).get("message");

                problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(statusCode), message);

                problemDetail.setInstance(URI.create((String) ((LinkedHashMap<?, ?>) body).get("path")));
                return problemDetail;

            }
        }

        if (body instanceof ProblemDetail) {
            return body;
        }

        return new ApiResponse<Object>(body, 200);
    }
}
