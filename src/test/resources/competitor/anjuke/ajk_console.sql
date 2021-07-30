drop table ajk_url_history;
create table ajk_url_history
(
    id            bigint auto_increment
        primary key,
    url           varchar(255) not null,
    params        varchar(255) not null,
    result        longtext     null comment 'json结果',
    create_time   datetime     null,
    CLASS_HANDLER varchar(255) null comment '类名。哪个类处理的',
    URL_BASE64    varchar(255) null comment '将URL通过BASE64加密得到的结果',
    IS_SUCCESS    int          null comment '当前请求是否成功'
)
    comment '存储已经采集过的url';


create index ajk_url_history1
    on ajk_url_history (URL_BASE64);

create index ajk_url_history2
    on ajk_url_history (create_time);