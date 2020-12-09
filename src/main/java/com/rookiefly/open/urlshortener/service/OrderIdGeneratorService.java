package com.rookiefly.open.urlshortener.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 生成日期开头加0001，0002，0003的工具类
 */
@Service
public class OrderIdGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(OrderIdGeneratorService.class);

    private static final String ID_KEY = "id:generator:orderid";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String generateOrderId() {
        return generateCode(ID_KEY, "ORDER", true, 6);
    }

    /**
     * @param key
     * @param prefix
     * @param hasExpire
     * @param minLength
     * @return String
     * @Title: generateCode
     * @Description: 生成自定义前缀的类似 HTG201810120001格式的自增数
     */
    public String generateCode(String key, String prefix, boolean hasExpire, Integer minLength) {
        return this.createGenerateCode(key, prefix, hasExpire, minLength);
    }

    /**
     * @param key       redis中的key值
     * @param prefix    最后编码的前缀
     * @param hasExpire redis 是否使用过期时间设置自增id
     * @param minLength redis生成的自增id的最小长度，如果小于这个长度前面补0
     * @return String
     * @Title: generateCode
     * @Description: 组装符合自己规则的id并设置过期时间
     */
    public String createGenerateCode(String key, String prefix, boolean hasExpire, Integer minLength) {
        try {
            Date date;
            Calendar calendar = Calendar.getInstance();
            if (hasExpire) {
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 9999);
                date = calendar.getTime();
            } else {
                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 10);
                date = calendar.getTime();
            }
            Long id = generateId(key, date);
            if (id != null) {
                return format(id, prefix, minLength);
            }
        } catch (Exception e) {
            logger.info("error --> redis 生成自增id出现异常");
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param key
     * @param date
     * @return Long
     * @Title: generateId
     * @Description: 使用RedisAtomicLong自增
     */
    private Long generateId(String key, Date date) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.expireAt(date);
        return counter.incrementAndGet();
    }

    /**
     * @param id        redis 获取的 id值
     * @param prefix    自定义前缀
     * @param minLength 生成数的长度，不满足时 0 补齐
     * @return String
     * @Title: format
     * @Description: 获取 redis 自增后，生成自定义格式的id
     */
    private String format(Long id, String prefix, Integer minLength) {

        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        sb.append(df.format(new Date())).append(StringUtils.leftPad(id.toString(), minLength, "0"));
        return sb.toString();
    }

}
