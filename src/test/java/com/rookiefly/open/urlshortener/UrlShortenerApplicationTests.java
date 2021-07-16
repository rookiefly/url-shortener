package com.rookiefly.open.urlshortener;

import com.rookiefly.open.urlshortener.mapper.LinksMapper;
import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.util.ConversionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UrlShortenerApplicationTests {

    @Resource
    private LinksMapper linksMapper;

    @Test
    public void testInsert() {
        Links links = new Links();
        links.setUrl("http://google.com");
        Assertions.assertEquals(1, linksMapper.insertLinks(links));
        long id = links.getId().longValue();
        Assertions.assertEquals(2L, id);
        String keyword = ConversionUtil.encode(id, 6);
        Assertions.assertEquals("000002", keyword);
        Assertions.assertEquals(1, linksMapper.update(keyword, id));
        Assertions.assertEquals("http://google.com", this.linksMapper.findByKeyword("000002").getUrl());

    }

}
