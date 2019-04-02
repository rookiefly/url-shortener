package com.rookiefly.open.urlshortener.util;

import org.junit.Assert;
import org.junit.Test;

public class ConversionUtilTest {

    @Test
    public void codecTest() {
        System.out.println("62进制：" + ConversionUtil.encode(916132832L, 6));
        System.out.println("62进制：" + ConversionUtil.encode(1L, 6));
        System.out.println("10进制：" + ConversionUtil.decode("100000"));
        Assert.assertEquals("100000", ConversionUtil.encode(916132832L, 6));
    }
}
