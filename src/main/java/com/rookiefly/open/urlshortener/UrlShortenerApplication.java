package com.rookiefly.open.urlshortener;

import com.rookiefly.open.urlshortener.mapper.LinksMapper;
import com.rookiefly.open.urlshortener.model.Links;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    private final LinksMapper linksMapper;

    public UrlShortenerApplication(LinksMapper linksMapper) {
        this.linksMapper = linksMapper;
    }

    @Override
    public void run(String... args) {
        System.out.println(this.linksMapper.findByKeyword("000001"));
//        Links links = new Links();
//        links.setKeyword("000002");
//        links.setUrl("google.com");
//        System.out.println(this.linksMapper.insertLinks(links));
//        System.out.println(this.linksMapper.findByKeyword("000002"));
    }
}
