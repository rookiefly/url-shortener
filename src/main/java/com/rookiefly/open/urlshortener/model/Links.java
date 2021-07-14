package com.rookiefly.open.urlshortener.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Links implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String url; //长连接

    private String keyword; //短链接码

    private Date gmtCreate; //插入时间

    private Date gmtModify; //更新时间
}