package com.springboot.district.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Cher on 2019-08-27
 */
public class CommonBusinessException extends RuntimeException {

    /**
     * 异常状态码
     */
    private int code = HttpStatus.BAD_REQUEST.value();

    /**
     * 异常描述信息
     */
    private String message;

    public CommonBusinessException() {
    }

    public CommonBusinessException(String message) {
        super(message);
        this.message = message;
    }

    public CommonBusinessException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    public CommonBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public CommonBusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
