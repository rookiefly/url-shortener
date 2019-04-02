package com.rookiefly.open.urlshortener.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class Links implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String url; //长连接

    private String keyword; //短链接码

    private Timestamp insertAt; //插入时间

    private Timestamp updatedAt; //更新时间
}