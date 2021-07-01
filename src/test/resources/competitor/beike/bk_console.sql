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


select count(1) from (select city_name from bk_fence where type = 'district' group by city_name) a;

select * from bk_fence where city_name = '海门市';

select count(1) from bk_fence;