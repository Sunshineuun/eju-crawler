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

create table eju_city_list
(
    id              bigint auto_increment primary key,
    city            varchar(36) not null comment '城市名称',
    code            varchar(36) not null comment 'eju城市编码',
    bk_code         varchar(36) comment 'bk城市编码',
    bk_pinyin_code  varchar(36) comment 'bk拼音',
    ajk_code        varchar(36) comment 'ajk城市编码',
    ajk_pinyin_code varchar(36) comment 'ajk拼音'
) comment '城市列表';
create index eju_city_list_index1 on eju_city_list (code);
create index eju_city_list_index2 on eju_city_list (city);

drop table crawler_url_history;
create table crawler_url_history
(
    id             bigint auto_increment
        primary key,
    task_id        bigint                             not null comment '属于的任务id',
    source         varchar(35)                        not null comment '来源。目前有bk',
    url_base64     varchar(255)                       null comment '将URL通过BASE64加密得到的结果',
    time_consuming bigint comment '耗时',
    url            varchar(1000)                      not null,
    class_handler  varchar(255)                       null comment '类名。哪个类处理的',
    is_success     int                                not null comment '当前请求是否成功',
    result         longtext                           not null comment 'json结果',
    create_time    datetime default CURRENT_TIMESTAMP null
);
create index crawler_url_history1 on crawler_url_history (url_base64);
create index crawler_url_history2 on crawler_url_history (create_time);
