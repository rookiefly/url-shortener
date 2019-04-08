package com.rookiefly.open.urlshortener.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private String shortUrl;

    private String longUrl;

    public ApiResponse() {
    }

    public ApiResponse(Integer code) {
        this.code = code;
    }

    public ApiResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg
        ;
    }

    public static ApiResponse newSuccess() {
        return new ApiResponse(200);
    }

    public static ApiResponse newParamError() {
        return new ApiResponse(400, "参数错误");
    }

    public static ApiResponse newNotFound() {
        return new ApiResponse(404, "链接不存在");
    }
}
