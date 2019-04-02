SET MODE MySQL; -- for h2 test

drop table if exists `links`;

create table `links`
(
    id      int primary key auto_increment,
    url    varchar(255),
    keyword   varchar(255),
    insert_at timestamp,
    updated_at timestamp
);

insert into links (id, url, keyword)
values (1, 'rookiefly.com', '1');
