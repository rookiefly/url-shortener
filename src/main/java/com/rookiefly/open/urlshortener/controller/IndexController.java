package com.rookiefly.open.urlshortener.controller;

import com.rookiefly.open.urlshortener.service.LinksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private LinksService linksService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/s/{keyword}", method = RequestMethod.GET)
    public String redirect(@PathVariable String keyword) {
        String longUrl = linksService.restoreShortUrl(keyword);
        if (StringUtils.isEmpty(longUrl)) {
            return "404";
        }
        return String.format("redirect:%s", longUrl);
    }
}
