package com.qiusm.eju.crawler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.qiusm.eju.crawler.entity.bk.BkDeal;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {

    public static void main(String[] args) {
        b();
    }

    static void a() {
        String a = "adjustments,property_ownership,goodes_id,plate,deal_time,task_id,total_floor,floor_height,title,elevator,cityname,build_year,property_year,property_type,housing_years,history_deal_price,viewcount,heating,ladder_ratio,strategy_info,build_type,decoration,resblock_id,area,goodes_name,orientation,batch_no,create_time,concern_count,file_name,deal_average_price,title_id,goods_id,deal_price,list_price,pv_count,layout,info_src,dwelling_floor_space,history_deal_desc,deal_cycle,trading_rights,create_user,region,layout_str,desc,frame_image,citycode,frame_url,frame_image_local,detail_url,frame_image_mark_remove";
        for (String s : a.split(",")) {
            String v1 = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
            System.out.println(String.format("private String %s;", v1));
        }
    }

    static void b() {
        String j = "{\"adjustments\":\"0\",\"plate\":\"北蔡\",\"deal_time\":\"2021.04.04\",\"total_floor\":\"20层\",\"title\":\"大华锦绣华城(十六街区)(公寓)\",\"floor_height\":\"地下室\",\"bizcircle_id\":\"611900123\",\"city_name\":\"上海\",\"limit_offset\":\"0\",\"elevator\":\"无\",\"house_name\":\"大华锦绣华城(十六街区)(公寓) 车位 32.75㎡\",\"build_year\":\"暂无数据\",\"property_type\":\"车库\",\"viewCount\":\"0\",\"history_deal_price\":\"37万\",\"price_bp\":0,\"strategy_info\":\"{\\\"fb_query_id\\\":\\\"470969337508372480\\\",\\\"fb_expo_id\\\":\\\"470969337659367424\\\",\\\"fb_item_location\\\":\\\"0\\\",\\\"fb_service_id\\\":\\\"1011710018\\\",\\\"fb_ab_test_flag\\\":\\\"\\\",\\\"fb_item_id\\\":\\\"107103800321\\\"}\",\"build_type\":\"板楼\",\"decoration\":\"毛坯\",\"resblock_id\":\"5011000014749\",\"area\":\"32.75\",\"orientation\":\"南\",\"concern_count\":\"0\",\"ele_id\":\"010ACA160BC0932DD27A01F02A2A723A_87a6895ed52bc4782a6d96aa9a894ca1\",\"deal_average_price\":\"11298元/平\",\"title_id\":\"5011000014749\",\"sign_date\":\"2021.04.04\",\"deal_price\":\"37万\",\"price_str\":\"37\",\"list_price\":\"40\",\"pv_count\":\"34\",\"house_code\":\"107103800321\",\"history_deal_desc\":\"单价11298元/平，2021.04.04\",\"price_ep\":50,\"unit_price_str\":\"11298元/平\",\"deal_cycle\":\"2\",\"district_id\":\"310115\",\"trading_rights\":\"商品房\",\"region\":\"浦东\",\"city_id\":\"310000\",\"desc\":\"南/地下室/20层/32.75㎡\"}\n";
        BkDeal json = JSONObject.parseObject(j, BkDeal.class);
        System.out.println(json);
//        ObjectMapper mapper=new ObjectMapper();
//        BkDeal user = mapper.convertValue(user, BkDeal.class);
    }
}
