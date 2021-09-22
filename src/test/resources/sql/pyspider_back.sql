-- 备份位置：E:\备份\20210922093810_ajk_url_his_community_detail.nb3；包含ajk0919～0922期间抓取的40w～50w数量的小区详情。
select *
from ajk_url_history;
-- 备份位置：E:\备份\tj_company_info_20210922.nb3
select *
from tj_company_info;
-- 备份位置：E:\备份\community_ke_strategy_20210922.nb3
truncate community_ke_strategy;
select *
from `community_ke_strategy`;
-- 备份位置：E:\备份\community_fangdi_house_json_old_20210922.nb3
truncate community_fangdi_house_json_old;
select *
from community_fangdi_house_json_old;
-- 备份位置：E:\备份\community_fangdi_house_json_20210922104340.nb3
truncate community_fangdi_house_json;
select *
from community_fangdi_house_json;
--
select *
from community_fangdi_house_detail_re_old;
-- 备份位置：E:\备份\fox_info_20210922.nb3
truncate fox_info;
select * from fox_info;

select table_schema                                   as '数据库',
       table_name                                     as '表名',
       table_rows                                     as '记录数',
       truncate(data_length / 1024 / 1024 / 1024, 2)  as '数据容量(GB)',
       truncate(index_length / 1024 / 1024 / 1024, 2) as '索引容量(GB)'
from information_schema.tables
where TABLE_SCHEMA = 'pyspider_db'
order by data_length desc, index_length desc;
