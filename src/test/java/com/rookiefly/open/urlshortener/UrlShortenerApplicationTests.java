package com.rookiefly.open.urlshortener;

import com.rookiefly.open.urlshortener.mapper.LinksMapper;
import com.rookiefly.open.urlshortener.model.Links;
import com.rookiefly.open.urlshortener.util.ConversionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortenerApplicationTests {

    @Resource
    private LinksMapper linksMapper;

    @Test
    public void testInsert() {
        Links links = new Links();
        links.setUrl("http://google.com");
        Assert.assertEquals(1, linksMapper.insertLinks(links));
        long id = links.getId().longValue();
        Assert.assertEquals(2L, id);
        String keyword = ConversionUtil.encode(id, 6);
        Assert.assertEquals("000002", keyword);
        Assert.assertEquals(1, linksMapper.update(keyword, id));
        Assert.assertEquals("http://google.com", this.linksMapper.findByKeyword("000002").getUrl());

    }

}
