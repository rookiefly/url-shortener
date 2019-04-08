package com.rookiefly.open.urlshortener.service;

import com.rookiefly.open.urlshortener.model.Links;

/**
 * 短链接操作接口
 */
public interface LinksService {

    String queryByShortUrl(String shortUrl);

    String insertLongUrl(Links links);
}