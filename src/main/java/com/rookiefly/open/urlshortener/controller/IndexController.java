package com.rookiefly.open.urlshortener.controller;

import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.monitor.PrometheusCustomMonitor;
import com.rookiefly.open.urlshortener.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Autowired
    private LinksService linksService;

    @Resource
    private PrometheusCustomMonitor monitor;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    public String shorten(@RequestParam String longUrl, Model model) {
        Links links = new Links();
        links.setUrl(longUrl);
        String shortUrl = linksService.insertShortUrl(links);
        model.addAttribute("shortUrl", shortUrl);
        monitor.getGenerateShortUrlCount().increment();
        return "results";
    }
}
