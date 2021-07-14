package com.rookiefly.open.urlshortener.service.impl;

import com.rookiefly.open.urlshortener.mapper.LinksMapper;
import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.service.LinksService;
import com.rookiefly.open.urlshortener.strategy.ShortUrlGeneratorStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LinksServiceImpl implements LinksService {

    @Value("${url-shortener.baseurl}")
    private String baseUrl;

    @Value("${url-shortener.strategy}")
    private String strategy;

    @Resource
    private LinksMapper linksMapper;

    @Resource
    private ShortUrlGeneratorStrategy shortUrlGeneratorStrategy;

    @Override
    @Cacheable(value = "urlCache", key = "targetClass + methodName + #shortUrl")
    public String restoreShortUrl(String shortUrl) {
        String keyword = StringUtils.substringAfterLast(shortUrl, "/");
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        Links links = linksMapper.findByKeyword(keyword);
        if (links == null) {
            return null;
        }
        return links.getUrl();
    }

    @Override
    public String insertShortUrl(Links links) {
        String longUrl = links.getUrl();
        Links existLinks = linksMapper.findByUrl(longUrl);
        if (existLinks != null && StringUtils.isNotEmpty(existLinks.getKeyword())) {
            return baseUrl + existLinks.getKeyword();
        } else {
            String keyword = shortUrlGeneratorStrategy.getStrategy(strategy).shortUrl(longUrl);
            links.setKeyword(keyword);
            linksMapper.insertLinks(links);
            return baseUrl + keyword;
        }
    }
}
