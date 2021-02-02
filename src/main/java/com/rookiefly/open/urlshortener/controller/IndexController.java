package com.rookiefly.open.urlshortener.controller;

import com.rookiefly.open.urlshortener.monitor.PrometheusCustomMonitor;
import com.rookiefly.open.urlshortener.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
