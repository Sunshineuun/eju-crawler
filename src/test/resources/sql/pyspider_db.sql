select table_schema                                   as '数据库',
       table_name                                     as '表名',
       table_rows                                     as '记录数',
       truncate(data_length / 1024 / 1024 / 1024, 2)  as '数据容量(GB)',
       truncate(index_length / 1024 / 1024 / 1024, 2) as '索引容量(GB)'
from information_schema.tables
where TABLE_SCHEMA = 'pyspider_db'
order by data_length desc, index_length desc;
create table community_detail
(
    id                    bigint primary key auto_increment,
    city                  varchar(50)  not null comment '城市名称',
    city_code             varchar(25)  not null comment '城市编码',
    region                varchar(50) comment '区域',
    region_code           varchar(50) comment '区域编码',
    plate                 varchar(50) comment '板块、商圈编码',
    plate_code            varchar(50) comment '板块、商圈编码',
    title                 varchar(255) not null comment '源小区名称',
    title_id              varchar(255) not null comment '源小区ID',
    alias                 varchar(255) not null comment '小区别名',
    current_base_url      varchar(255) not null comment '访问该小区的url',
    lng                   varchar(50) comment '经度',
    lat                   varchar(50) comment '纬度',
    average_price         varchar(50) comment '小区均价',
    property_year         varchar(50) comment '产权年限',
    trading_rights        varchar(50) comment '交易权属',

    sale                  varchar(50) comment '在售数量,num_for_sale',
    rent                  varchar(50) comment '在租数量,rent_num',
    gate                  varchar(50) comment '小区入口',
    loop_position         varchar(50) comment '所处环线位置',
    loudong_num           varchar(50) comment '楼栋数量',
    home_total            varchar(50) comment '总户数',

    build_year            varchar(50) comment '竣工日期',
    build_type            varchar(50) comment '建筑类型',
    build_str             varchar(50) comment '建筑结构',

    property_price        varchar(50) comment '物业费',
    property_company      varchar(50) comment '物业公司',
    property_phone        varchar(50) comment '物业电话',
    property_type         varchar(50) comment '物业类型',
    pro_service_addr      varchar(50) comment '物业办公地点',
    pro_on_time           varchar(50) comment '开盘时间',
    park_num              varchar(50) comment '停车位',
    upper_num             varchar(50) comment '地上车位数',
    under_num             varchar(50) comment '地下车位数',
    park_ratio            varchar(50) comment '车位配比',
    fixed_charge          varchar(50) comment '停车费',
    parking_sale_ind      varchar(50) comment '在售停车位',

    green_rate            varchar(50) comment '绿化率',
    plot_rate             varchar(50) comment '容积率',
    water                 varchar(50) comment '用水类型',
    supply_electricity    varchar(50) comment '用电类型',
    heating               varchar(50) comment '供暖类型',
    heating_cost          varchar(50) comment '供暖费用',
    gas                   varchar(50) comment '燃气费用',
    uses                  varchar(50) comment '房屋用途',
    build_area            varchar(50) comment '总面积',
    act_area              varchar(50) comment '户型面积',
    air_condition_ind     varchar(50) comment '',
    ext_wall_style        varchar(50) comment '外墙风格',
    facade_style          varchar(50) comment '立面风格',
    introduction          varchar(50) comment '项目简介',
    arch_des              varchar(50) comment '',
    building_des          varchar(50) comment '楼栋描述',
    loudong_url           varchar(50) comment '',
    layout_url            varchar(50) comment '',
    detail_url            varchar(50) comment '',
    railway_adress        varchar(50) comment '地铁',
    community_close_ind   varchar(50) comment '',
    person_div_car_ind    varchar(50) comment '人车飞流',
    nonmotor_garage_ind   varchar(50) comment '机动车非机动车分流',

    security_booth_num    varchar(50) comment '保安亭数',
    security_person_num   varchar(50) comment '保安人数',
    security_patrol_num   varchar(50) comment '巡逻人数',
    intel_gate_ind        varchar(50) comment '',
    security_allday_ind   varchar(50) comment '',
    gate_control_ind      varchar(50) comment '',
    monitor_ind           varchar(50) comment '',
    police_networking_ind varchar(50) comment '',
    brand_corp            varchar(50) comment '',
    series                varchar(50) comment '',
    fist_page_url         varchar(50) comment '',
    region_url            varchar(50) comment '',
    security_desc         varchar(50) comment '安全管理',
    communication_dev     varchar(50) comment '通讯设备',
    postcodes             varchar(50) comment '邮政编码',
    health_desc           varchar(50) comment '卫生服务',
    other                 varchar(50) comment '其它',
    elevator_desc         varchar(50) comment '电梯数量',
    nid                   varchar(50) comment '',
    loop_price            varchar(50) comment '',
    duikou_school         varchar(50) comment '对口学校',
    create_time           datetime
);

-- 19城+佛山列表
/*
上海,310000'310000
北京,110000'110000
南京,320100'320100
合肥,340100'340100
天津,120000'120000
宁波,330200'330200
成都,510100'510100
无锡,320200'320200
昆明,530100'530100
武汉,420100'420100
沈阳,210100'210100
济南,370101'370101
西安,610100'610100
郑州,410100'410100
重庆,500000'500000
广州,440100'440100
苏州,320500'320500
佛山,440600
厦门,350200'
杭州,330100
*/
select distinct city_name, city_id
from community_ke_tag
where platform_type = 'BK'
  and city_name in
      ('上海',
       '昆明',
       '北京',
       '成都',
       '天津',
       '合肥',
       '济南',
       '广州',
       '南京',
       '无锡',
       '苏州',
       '郑州',
       '宁波',
       '杭州',
       '武汉',
       '厦门',
       '沈阳',
       '重庆',
       '西安',
       '佛山');

-- 安居客小区列表交付克而瑞数据查询
select community,
       community_id,
       community_add,
       avg_price,
       avg_price_month,
       avg_price_month_change,
       property_company,
       property_expenses,
       property_type,
       volume_rate,
       greening_rate,
       park_space,
       dev_ent,
       build_year,
       city,
       region,
       plate,
       total_area,
       total_house
from community
where source = 'AJK'
  and property_company != '';
-- 物业公司不为空的小区
select count(1)
from community
where source = 'AJK'
  and property_company != '';
-- 物业公司为空的小区
select count(1)
from community
where source = 'AJK'
  and property_company = '';
update community
set property_company = null
where source = 'AJK'
  and property_company = '';

-- 贝壳成交数据交付朱骊
select *
from bk_deal_pc;