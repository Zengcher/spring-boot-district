package com.springboot.district.exception;

import com.springboot.district.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Cher on 2019-08-27
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
                                                                  HttpServletRequest request) {
        String message = "缺失参数" + e.getParameterName();
        return saveErrorAndSend(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Response handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                              HttpServletRequest request) {
        String message = "参数" + e.getName() + "类型错误";
        return saveErrorAndSend(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public Response handleHttpMessageNotReadableException(HttpMessageConversionException e,
                                                          HttpServletRequest request) {
        String message = "参数类型错误";
        return saveErrorAndSend(request, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * 通用业务异常
     */
    @ExceptionHandler(CommonBusinessException.class)
    @ResponseBody
    public Response handleCommonBusinessException(CommonBusinessException e, HttpServletRequest request) {
        return saveErrorAndSend(request, e.getMessage(), e.getCode(), e, true);
    }

    private Response saveErrorAndSend(HttpServletRequest request,
                                      String message,
                                      int code,
                                      Exception e,
                                      boolean logStackTrace) {
        Response response = Response.error(message, code);
        if (logStackTrace) {
            log.error(message, e);
        } else {
            log.error(message);
        }
        return response;
    }

    private Response saveErrorAndSend(HttpServletRequest request, String message, int code, Exception e) {
        return saveErrorAndSend(request, message, code, e, false);
    }

}
