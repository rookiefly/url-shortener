package com.rookiefly.open.urlshortener.controller;

import com.rookiefly.open.urlshortener.model.ApiResponse;
import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.monitor.PrometheusCustomMonitor;
import com.rookiefly.open.urlshortener.service.LinksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短链接API接口
 */
@CrossOrigin(origins = "*", methods = RequestMethod.POST)
@RestController
public class ApiController {

    @Autowired
    private LinksService linksService;

    @Resource
    private PrometheusCustomMonitor monitor;

    /**
     * 短链接生成接口
     *
     * @param url 原始url
     * @return 短链接url
     */
    @RequestMapping(value = "/api/shorten", method = RequestMethod.POST)
    public ApiResponse generateShortUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return ApiResponse.newParamError();
        }
        Links links = new Links();
        links.setUrl(url);
        String shortUrl = linksService.insertShortUrl(links);
        ApiResponse response = ApiResponse.newSuccess();
        response.setShortUrl(shortUrl);
        monitor.getGenerateShortUrlCount().increment();
        return response;
    }

    /**
     * 短链接还原接口
     *
     * @param shortUrl 短链接url
     * @return 原始url
     */
    @RequestMapping(value = "/api/query", method = RequestMethod.POST)
    public ApiResponse queryShortUrl(String shortUrl) {
        if (StringUtils.isEmpty(shortUrl)) {
            return ApiResponse.newParamError();
        }
        String longUrl = linksService.queryByShortUrl(shortUrl);
        if (StringUtils.isEmpty(longUrl)) {
            return ApiResponse.newNotFound();
        }
        ApiResponse response = ApiResponse.newSuccess();
        response.setLongUrl(longUrl);
        return response;
    }
}
