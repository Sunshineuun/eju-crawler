create table crawler_url
(
    id          bigint auto_increment
        primary key,
    url         varchar(255) not null comment '请求的url',
    type        varchar(255) not null,
    success     char         not null,
    create_time datetime default current_timestamp on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '对于请求过的url进行记录，以便后续做统计分析';