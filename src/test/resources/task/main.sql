create table crawler_task
(
    id              bigint auto_increment primary key,
    task_name       varchar(255) comment '任务名称（抓取任务的简单描述）',
    task_param_json varchar(1000) comment '任务参数，json格式',
    handler_type    varchar(35) comment '处理器标识，程序通过该标识识别当前任务是否应该归属自己处理'
) comment '任务列表';
create table crawler_task_instance
(
    id                         bigint auto_increment primary key,
    task_id                    bigint,
    task_name                  varchar(255) comment '任务名称（抓取任务的简单描述）',
    task_param_json            varchar(1000) comment '任务参数，json格式',
    handler_type               varchar(35) comment '处理器标识，程序通过该标识识别当前任务是否应该归属自己处理',
    handle_request_num         bigint comment '当前任务处理请求数量',
    handle_request_failure_num bigint comment '当前任务处理请求失败的数量',
    handle_request_success_num bigint comment '当前任务处理请求成功的数量',
    create_time                datetime comment '任务创建时间'
) comment '任务实例列表';