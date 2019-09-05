package com.springboot.district.advice;

import com.springboot.district.response.ExtraPageImpl;
import com.springboot.district.response.PageData;
import com.springboot.district.response.Pagination;
import com.springboot.district.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
@RestControllerAdvice
@Slf4j
public class PageResponseHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Method method = returnType.getMethod();
        if (method != null && method.getDeclaredAnnotation(PureResponseBody.class) != null) {
            return false;
        }
        return method != null
                && (method.getReturnType() == Page.class
                || method.getReturnType() == PageImpl.class
                || method.getReturnType() == ExtraPageImpl.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof ExtraPageImpl) {
            ExtraPageImpl extraPage = (ExtraPageImpl) body;
            List<Object> content = CollectionUtils.isEmpty(extraPage.getContent())
                    ? Collections.emptyList()
                    : extraPage.getContent();
            PageData<Object, Object> pageData = new PageData<>(Pagination.of(extraPage), content, extraPage.getExtra());
            return Response.success(pageData);
        }

        if (body instanceof Page) {
            Page page = (Page) body;
            List<Object> content = CollectionUtils.isEmpty(page.getContent())
                    ? Collections.emptyList()
                    : page.getContent();
            PageData<Object, Object> pageData = new PageData<>(Pagination.of(page), content, null);
            return Response.success(pageData);
        }

        return Response.success(body);
    }
}