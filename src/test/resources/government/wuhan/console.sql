/*
数据库地址：10.122.139.25
数据库：pyspider
*/
/*
链接数计算：
翻页：300；
楼盘明细：6000；
楼盘列表：6000；
房屋列表：55220
房屋明细：315853
*/
/*
    fd_house,fd_gtz,fd_ghxkz,fd_building,fd_unit
*/
create table fd_wuhan_house···
(
    id                                  bigint auto_increment
    primary key,
    city_name                           varchar(255) null,
    project_id                          varchar(50)  null,
    project_name                        varchar(255) null comment '项目名称',
    project_address                     varchar(255) null comment '项目地址',
    APPROVAL_PRESALE_HOUSE_NUM          varchar(255) null comment '批准销售套数',
    house_sold_num                      varchar(255) null comment '住宅已售套数',
    house_unsale_num                    varchar(255) null comment '住宅未售套数',
    non_house_sold_num                  varchar(255) null comment '非住宅已售套数',
    non_house_unsold_num                varchar(255) null comment '非住宅未售套数',
    contract_record_handling_department varchar(255) null comment '合同备案办理部门',
    url                                 varchar(255) null comment '请求地址',
    status                              varchar(255) null comment '操作状态',
    version                             varchar(255) null comment '版本号',
    create_time                         datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
    )
    comment '武汉-政府源-基础信息';
create table fd_wuhan_gtz
(
    id          bigint auto_increment
        primary key,
    house_id    bigint comment 'fd_house.id',
    city_name   varchar(255) null,
    project_id  varchar(50)  null,
    gtz_no      varchar(255) null comment '国土证号',
    url         varchar(255) null comment '请求地址',
    status      varchar(255) null comment '操作状态',
    version     varchar(255) null comment '版本号',
    create_time datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
) comment '武汉-政府源-国土证';
create table fd_wuhan_ghxkz
(
    id                      bigint auto_increment
        primary key,
    house_id                bigint comment 'fd_house.id',
    city_name               varchar(255) null,
    project_id              varchar(50)  null,
    ghxkz_construction_unit varchar(255) null comment '建设单位',
    ghxkz_no                varchar(255) null comment '建筑规划许可证号',
    builnding_num           varchar(255) null comment '栋数',
    base_area               varchar(255) null comment '基地面积',
    households_num          varchar(255) null comment '居住户数',
    url                     varchar(255) null comment '请求地址',
    status                  varchar(255) null comment '操作状态',
    version                 varchar(255) null comment '版本号',
    create_time             datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
) comment '武汉-政府源-规划许可证';
create table fd_wuhan_kfs
(
    id                 bigint auto_increment
        primary key,
    house_id           bigint comment 'fd_house.id',
    city_name          varchar(255) null,
    project_id         varchar(50)  null,
    kfs_contact_number varchar(255) null comment '开发商联系电话',
    url                varchar(255) null comment '请求地址',
    status             varchar(255) null comment '操作状态',
    version            varchar(255) null comment '版本号',
    create_time        datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
) comment '武汉-政府源-开发商';
create table fd_wuhan_building
(
    id                         bigint auto_increment
        primary key,
    house_id                   bigint comment 'fd_house.id',
    city_name                  varchar(255) null comment '城市名称',
    project_id                 varchar(255) null comment '项目id',
    project_name               varchar(255) null comment '项目名称',
    building_id                varchar(255) null comment '楼栋id',
    building_name              varchar(255) null comment '楼栋名称',
    building_total_layer_num   varchar(255) null comment '楼栋总层数',
    building_house_num         varchar(255) null comment '楼栋总套数',
    building_area              varchar(255) null comment '楼栋建筑面积',
    surveying_mapping_agencies varchar(255) null comment '测绘机构',
    url                        varchar(255) null comment '请求地址',
    status                     varchar(255) null comment '操作状态',
    version                    varchar(255) null comment '版本号',
    create_time                datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '武汉-政府源-楼栋信息';
create table fd_wuhan_unit
(
    id                     bigint auto_increment
        primary key,
    house_id               bigint comment 'fd_house.id',
    building_id            bigint comment 'fd_building.id',
    city_name              varchar(255) null comment '城市名称',
    project_id             varchar(255) null comment '项目id',
    project_name           varchar(255) null comment '项目名称',
    building_name          varchar(255) null comment '楼栋名称',
    unit_id                varchar(255) null comment '单元ID',
    nominal_floor          varchar(255) null comment '名义层号 -- 销售楼层',
    room_no                varchar(255) null comment '房号',
    house_address          varchar(255) null comment '房屋地址',
    presell_no             varchar(255) null comment '预售证号',
    measured_total_area    varchar(255) null comment '实测总面积 -- 建筑面积',
    pre_building_avg_price varchar(255) null comment '拟售单价（建面）',
    house_total_price      varchar(255) null comment '房屋总价',
    house_sales_status     varchar(255) null comment '房屋销售状态',
    url                    varchar(255) null comment '请求地址',
    details_url            varchar(255) null comment '详情url',
    status                 varchar(255) null comment '操作状态',
    version                varchar(255) null comment '版本号',
    create_time            datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '武汉-政府源-楼栋信息';
create table fd_wuhan_url
(
    id          bigint auto_increment
        primary key,
    url         varchar(255) not null comment '请求的url',
    type        varchar(255) not null,
    success     char(1)      not null,
    create_time datetime     null on update CURRENT_TIMESTAMP comment '创建时间'
)
    comment '武汉-政府源-请求过url的记录';

SELECT *
FROM fd_wuhan_house;
select *
from fd_wuhan_gtz;
select *
from fd_wuhan_ghxkz;
select *
from fd_wuhan_kfs;
select *
from fd_wuhan_building;
select count(1), house_id
from fd_wuhan_unit
group by house_id;


-- 项目与房屋之间的：实际套数与预计套数进行比较
select house.id,
       house.project_name,
       house.project_id,
       house.APPROVAL_PRESALE_HOUSE_NUM           预计套数,
       unit.c1                                    实际套数,
       house.approval_presale_house_num = unit.c1 stat,
       house.create_time
from fd_wuhan_house house
         left join
     (select count(1) c1, house_id
      from fd_wuhan_unit
      where (details_url is null)
         or (details_url is not null and house_sales_status is not null)
      group by house_id) unit
     on house.id = unit.house_id;
-- 楼栋与房屋之间的：实际套数与预计套数进行比较
select *
from (
         select building.id,
                building.project_id,
                building.building_name,
                building.building_house_num,
                unit.unit_num,
                building.building_house_num = unit.unit_num state,
                building.url
         from fd_wuhan_building building
                  left join (select count(1) unit_num, building_id
                             from fd_wuhan_unit
                             group by building_id) unit
                            on unit.building_id = building.id
     ) a
where state != 1
   or state is null;
-- 项目与楼栋之间的：总套数比较，如果有差异则删除重新跑。
select *
from (
         select house.id,
                house.project_name,
                house.project_id,
                building_num                               楼栋数量,
                house.APPROVAL_PRESALE_HOUSE_NUM           预计套数,
                unit.c1                                    实际套数,
                house.approval_presale_house_num = unit.c1 stat,
                house.create_time
         from (select * from fd_wuhan_house where project_id not like '%-back') house
                  left join
              (select sum(building_house_num) c1, house_id, count(1) building_num, max(building_house_num)
               from fd_wuhan_building
               group by house_id) unit
              on house.id = unit.house_id
     ) a;
-- 将数据不正确的更新为 projectId + '-back'
update fd_wuhan_house
set project_id = concat(project_id, '-back')
where id in (
    select a.id
    from (
             select house.id,
                    house.project_name,
                    house.project_id,
                    house.APPROVAL_PRESALE_HOUSE_NUM           预计套数,
                    unit.c1                                    实际套数,
                    house.approval_presale_house_num = unit.c1 stat,
                    house.create_time
             from fd_wuhan_house house
                      left join
                  (select sum(building_house_num) c1, house_id
                   from fd_wuhan_building
                   group by house_id) unit
                  on house.id = unit.house_id
         ) a
    where a.stat = 0
       or a.stat is null
)
  and project_id not like '%back';
update fd_wuhan_gtz
set project_id = concat(project_id, '-back')
where house_id in (select id
                   from fd_wuhan_house
                   where project_id like '%-back');
-- 房屋有明细，有明细采集完毕和采集结束，房屋无明细
select count(1) num, '有明细.待处理' type
from fd_wuhan_unit
where details_url is not null
  and house_address is null
  and status != 'failure'
union all
select count(1), '有明细.已处理'
from fd_wuhan_unit
where details_url is not null
  and house_address is not null
union all
select count(1), '无明细'
from fd_wuhan_unit
where details_url is null;
-- 详情 房屋有明细需要处理的
select id,
       project_id,
       house_address,
       details_url,
       status
from fd_wuhan_unit
where details_url is not null
  and house_address is null
  and status = 'try';
-- 统计 房屋有明细，有明细采集完毕和采集结束，房屋无明细
select count(1) num, '有明细.待处理' type
from fd_wuhan_unit
where details_url is not null
  and house_address is null
union all
select count(1), '有明细.已处理'
from fd_wuhan_unit
where details_url is not null
  and house_address is not null
union all
select count(1), '无明细'
from fd_wuhan_unit
where details_url is null;
-- url时间分布
select count(1) c, a
from (SELECT date_format(create_time, '%y-%m-%d %H') a
      FROM fd_wuhan_url
      where create_time between '2021-06-22' and '2021-06-30') url
group by a;
-- 楼栋待加载房屋信息列表
select *
from (
         select building.*,
                building.building_house_num <= unit.unit_num state
         from fd_wuhan_building building
                  left join (select count(1) unit_num, building_id
                             from fd_wuhan_unit
                             group by building_id) unit
                            on unit.building_id = building.id
     ) a
where (state = 0 or state is null)
  and id > 10000
order by id
    limit 1000;
/*
异常情况记录：1061，预计套数36，实际套数52。已核实。
*/
-- 可以删除的SQL
-- 楼栋预计套数与房屋实际套数比较
select *
from (
         select building.building_house_num,
                unit.unit_num,
                building.building_house_num <= unit.unit_num state,
                building.house_id,
                building.id,
                building.project_id,
                building.building_name,
                building.url
         from fd_wuhan_building building
                  left join (select count(1) unit_num, building_id
                             from fd_wuhan_unit
                             group by building_id) unit
                            on unit.building_id = building.id
     ) a
where state = 0
   or state is null
order by id;

create table fd_wuhan_unit_20210621 as
select *
from fd_wuhan_unit;
create table fd_wuhan_building_20210621 as
select *
from fd_wuhan_building;


select *
from fd_wuhan_building
where building_id = 26;
select *
from fd_wuhan_unit
where building_id = 2774;
select *
from fd_wuhan_unit
where details_url is not null
order by create_time desc;


/*
楼盘列表：http://119.97.201.22:8083/search/spfxmcx/spfcx_lpb.aspx?DengJH=%D0%C22100431
房屋列表：http://119.97.201.22:8083/search/spfxmcx/spfcx_fang.aspx?dengJH=%D0%C22100431&houseDengJh=%D0%C20004024
*/
select url
from fd_wuhan_building_20210621;

select id,
       building_id,
       house_address,
       presell_no,
       measured_total_area,
       pre_building_avg_price,
       house_total_price
from fd_wuhan_unit
where building_id = 458
  and details_url is not null;


show OPEN TABLES where In_use > 0;

show processlist;
kill 661365;

