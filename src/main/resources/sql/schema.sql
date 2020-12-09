create table if not exists `links`
(
    id      bigint primary key auto_increment,
    url    varchar(255)  comment '原始链接',
    keyword   varchar(255)  comment '短链接码',
    gmt_create timestamp not null default current_timestamp comment '创建时间',
    gmt_modify timestamp not null default current_timestamp on update current_timestamp comment '修改时间'
);
