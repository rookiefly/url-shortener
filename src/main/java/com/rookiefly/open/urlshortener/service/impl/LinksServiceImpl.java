package com.rookiefly.open.urlshortener.service.impl;

import com.rookiefly.open.urlshortener.mapper.LinksMapper;
import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.service.LinksService;
import com.rookiefly.open.urlshortener.util.ConversionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinksServiceImpl implements LinksService {

    @Value("${url-shortener.baseurl}")
    private String baseUrl;

    @Autowired
    private LinksMapper linksMapper;

    @Override
    public String queryByShortUrl(String shortUrl) {
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
    public String insertLongUrl(Links links) {
        String longUrl = links.getUrl();
        Links existLinks = linksMapper.findByUrl(longUrl);
        if (existLinks != null && StringUtils.isNotEmpty(existLinks.getKeyword())) {
            return baseUrl + existLinks.getKeyword();
        } else if (existLinks != null) {
            links = existLinks;
        } else {
            linksMapper.insertLinks(links);
        }
        long id = links.getId().longValue();
        String keyword = ConversionUtil.encode(id, 6);
        linksMapper.update(keyword, id);
        return baseUrl + keyword;
    }
}
