package com.rookiefly.open.urlshortener.controller;

import com.rookiefly.open.urlshortener.model.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接API接口
 */
@RestController
public class LinksController {

    /**
     * 短链接生成接口
     *
     * @param url 原始url
     * @return 短链接url
     */
    @RequestMapping(value = "/admin/shorten", method = RequestMethod.POST)
    public ApiResponse generateShortUrl(String url) {
        return ApiResponse.newSuccess();
    }

    /**
     * 短链接还原接口
     *
     * @param shortUrl 短链接url
     * @return 原始url
     */
    @RequestMapping(value = "/admin/query", method = RequestMethod.POST)
    public ApiResponse queryShortUrl(String shortUrl) {
        return ApiResponse.newSuccess();
    }
}
