package com.rookiefly.open.urlshortener.service;

import com.rookiefly.open.urlshortener.model.Links;

/**
 * 短链接操作接口
 */
public interface LinksService {

    String restoreShortUrl(String keyword);

    String insertShortUrl(Links links);
}