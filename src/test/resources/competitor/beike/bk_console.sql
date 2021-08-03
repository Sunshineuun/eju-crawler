-- 常用查询sql
-- 查询总量
select count(id)
from bk_url_history;
-- 查询成功 & 失败的数据数据情况
select count(IF(IS_SUCCESS = 0, 1, null)) f,
       count(IF(IS_SUCCESS = 1, 1, null)) f
from bk_url_history;
-- 查询每日新增请求数量
select count(id) c, date_format(create_time, '%Y-%m-%d') as create_time
from bk_url_history
group by date_format(create_time, '%Y-%m-%d');

-- 区域、板块围栏数据
drop table bk_fence;
create table bk_fence
(
    id        bigint primary key auto_increment,
    city_code char(6)      not null comment '城市编码',
    city_name varchar(10)  not null comment '城市名称',
    district  varchar(25)  not null comment '区信名称',
    bizcircle varchar(25) comment '商圈名称',
    longitude varchar(100) not null comment '经度',
    latitude  varchar(100) not null comment '纬度',
    fence     longtext     not null comment '围栏数据',
    type      varchar(35)  not null comment '区域:district;商圈:bizcircle',
    version   varchar(35)
) comment '贝壳围栏信息，其中包含城市、区域、商圈';

-- 小区、楼栋、单元、房号数据
/*
21,贝壳板块,community_plate,secondhandhouse.BeikeBankuaiUrlParser,ke_plate_app_url_code,BK,
22,贝壳小区列表,"",secondhandhouse.BeikeXiaoquListAppParser,ke_xiaoqu_app_list_code,BK,21
23,贝壳小区信息,community_base_info_list,secondhandhouse.BeikeXiaoquAppParser,ke_xiaoqu_app_code,BK,22
24,贝壳小区m端详情,"",secondhandhouse.BeikeXiaoquMDetailParser,ke_xiaoqu_m_detail_code,BK,23
25,贝壳小区pc详情,"",secondhandhouse.BeikeXiaoquPcDetailParser,ke_xiaoqu_pc_detail_code,BK,24
26,贝壳小区链家m详情,"",secondhandhouse.BeikeXiaoquLianjiaMDetailParser,ke_xiaoqu_lianjia_m_detail_code,BK,25
27,贝壳小区楼栋,community_building_base,secondhandhouse.BeikeBuildingParser,ke_building_app_code,BK,
28,贝壳楼栋集合,,secondhandhouse.BeikeBuildingListParser,ke_building_list_app_code,BK,
29,贝壳单元,community_unit,secondhandhouse.BeikeUnitParser,ke_unit_app_code,BK,
30,贝壳room,community_room,secondhandhouse.BeikeRoomParser,ke_room_app_code,BK,
*/

/*
逻辑，发送请求钱先去数据库中查找下，是否有相应的结果，如果有，就使用数据库中的；
如果查找的是空的那么删除再进行请求，请求成功后进行存储，存储之前判断结果是否为空的，为空则不进行存储
*/
create table bk_deal_url_history
(
    id          bigint auto_increment primary key,
    url         varchar(1000) not null,
    result      longtext comment 'json结果',
    create_time datetime
) comment '存储已经采集过的url';

alter table bk_deal_url_history
    add column CLASS_HANDLER VARCHAR(255) COMMENT '类名。哪个类处理的';
alter table bk_deal_url_history
    add column URL_BASE64 VARCHAR(255) COMMENT '将URL通过BASE64加密得到的结果';
alter table bk_deal_url_history
    add column IS_SUCCESS int COMMENT '当前请求是否成功';
create index bk_deal_history_index on bk_deal_url_history (URL_BASE64);