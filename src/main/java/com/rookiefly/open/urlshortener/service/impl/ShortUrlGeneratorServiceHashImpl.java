package com.rookiefly.open.urlshortener.service.impl;

import com.rookiefly.open.urlshortener.service.ShortUrlGeneratorService;
import com.rookiefly.open.urlshortener.util.ShortUrlGeneratorUtil;
import org.springframework.stereotype.Service;

/**
 * 基于md5 hash实现的算法
 */
@Service("hash")
public class ShortUrlGeneratorServiceHashImpl implements ShortUrlGeneratorService {

    @Override
    public String shortUrl(String url) {
        return ShortUrlGeneratorUtil.shortUrl(url);
    }
}
