SET MODE MySQL; -- for h2 test

drop table if exists `links`;

create table `links`
(
    id      bigint primary key auto_increment,
    url    varchar(255),
    keyword   varchar(255),
    gmt_create timestamp,
    gmt_modify timestamp
);
