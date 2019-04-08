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

    public static ApiResponse newSuccess() {
        return new ApiResponse(200);
    }
}
