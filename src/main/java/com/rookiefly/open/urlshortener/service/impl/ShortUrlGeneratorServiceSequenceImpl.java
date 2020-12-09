package com.rookiefly.open.urlshortener.service.impl;

import com.rookiefly.open.urlshortener.service.ShortUrlGeneratorService;
import com.rookiefly.open.urlshortener.util.ConversionUtil;
import org.springframework.stereotype.Service;

/**
 * 基于自增序列实现的算法
 */
@Service("sequence")
public class ShortUrlGeneratorServiceSequenceImpl implements ShortUrlGeneratorService {

    @Override
    public String shortUrl(String url) {
        long id = 0L;
        return ConversionUtil.encode(id, 6);
    }
}
