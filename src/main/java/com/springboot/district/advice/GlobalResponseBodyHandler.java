package com.springboot.district.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.district.response.ExtraPageImpl;
import com.springboot.district.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * Created by Cher on 2019-09-05
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper mObjectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Method method = returnType.getMethod();
        if (method != null && method.getDeclaredAnnotation(PureResponseBody.class) != null) {
            return false;
        }
        return method != null
                && method.getReturnType() != Response.class
                && method.getReturnType() != Page.class
                && method.getReturnType() != PageImpl.class
                && method.getReturnType() != ExtraPageImpl.class
                && method.getReturnType() != ResponseEntity.class;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof String) {
            try {
                return mObjectMapper.writeValueAsString(Response.success(body));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                return Response.error(e.getMessage());
            }
        }

        if (body instanceof Response) {
            return body;
        }

        return Response.success(body);
    }
}
