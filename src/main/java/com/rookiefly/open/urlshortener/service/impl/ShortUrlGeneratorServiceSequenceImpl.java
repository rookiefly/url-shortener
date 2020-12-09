package com.rookiefly.open.urlshortener.service.impl;

import com.rookiefly.open.urlshortener.service.SequenceIdGeneratorService;
import com.rookiefly.open.urlshortener.service.ShortUrlGeneratorService;
import com.rookiefly.open.urlshortener.util.ConversionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基于自增序列实现的算法
 */
@Service("sequence")
public class ShortUrlGeneratorServiceSequenceImpl implements ShortUrlGeneratorService {

    @Resource
    private SequenceIdGeneratorService sequenceIdGeneratorService;

    @Override
    public String shortUrl(String url) {
        long id = sequenceIdGeneratorService.generateSequenceId();
        return ConversionUtil.encode(id, 6);
    }
}
