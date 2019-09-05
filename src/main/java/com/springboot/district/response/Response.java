package com.springboot.district.response;

import lombok.Data;

/**
 * Created by Cher on 2019-09-05
 */
@Data
public class Response<T> {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private static final int USELESS_CODE = 0;

    private String status;
    private T data;
    private String message;
    private int code;

    Response(T data) {
        this.status = SUCCESS;
        this.data = data;
        this.message = "";
        this.code = 0;
    }

    Response(String message, int code) {
        this.status = ERROR;
        this.data = null;
        this.message = message;
        this.code = code;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> error(String message, int code) {
        return new Response<>(message, code);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(message, USELESS_CODE);
    }

}
