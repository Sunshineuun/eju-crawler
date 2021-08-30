package com.qiusm.eju.crawler.competitor.wawj;

import com.qiusm.eju.crawler.constant.EjuConstant;
import com.qiusm.eju.crawler.utils.http.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 我爱我家小区列表抓取
 *
 * @author qiushengming
 */
public class WawjCommunityTest {
    private static OkHttpUtils httpClient = OkHttpUtils.Builder()
            .proxyUrl(EjuConstant.PROXY_URL1)
            .addProxyRetryTag()
            .builderHttp();

    public static void main(String[] args) {
        a();
    }

    /**
     * 滨江 303
     */
    static void a() {
        String url = "https://hz.5i5j.com/xiaoqu/n2/";
        Map<String, String> map = new HashMap<>();
        map.put("Cookie", "PHPSESSID=3vu82kit409nn6imr28jtfgpp3; pc_pmf_group_sh=5bb9022effecf788fe3584a0a87a7e885a422d5d6d19826bfd6d3dd4e3e05dc6a%3A2%3A%7Bi%3A0%3Bs%3A15%3A%22pc_pmf_group_sh%22%3Bi%3A1%3Bs%3A154%3A%22%7B%22pmf_group%22%3A%22baidu%22%2C%22pmf_medium%22%3A%22ppzq%22%2C%22pmf_plan%22%3A%22%5Cu5de6%5Cu4fa7%5Cu6807%5Cu9898%22%2C%22pmf_unit%22%3A%22%5Cu6807%5Cu9898%22%2C%22pmf_keyword%22%3A%22%5Cu6807%5Cu9898%22%2C%22pmf_account%22%3A%22179%22%7D%22%3B%7D; _ga=GA1.2.1582681045.1630201867; _gid=GA1.2.1156420302.1630201867; Hm_lvt_94ed3d23572054a86ed341d64b267ec6=1630201868; gr_user_id=ee547053-b657-43d6-a75b-354801d45296; 8fcfcf2bd7c58141_gr_session_id=afc5e34b-2757-4552-a9d8-0bf15e75daf5; 8fcfcf2bd7c58141_gr_session_id_afc5e34b-2757-4552-a9d8-0bf15e75daf5=true; smidV2=2021082909510749322f30a55bd4adbf1a749e5f73757400234968afc9a5340; domain=hz; historyCity=%5B%22%5Cu676d%5Cu5dde%22%2C%22%5Cu4e0a%5Cu6d77%22%5D; _dx_uzZo5y=f6bfcc6a04012cbbc0a0ad5e169f61883f03eeb40ba2f6b086d0a754e9264451ede99ac6; HMF_CI=ba66f11381ece839849c807edeaa0c65756f295db92aa6ababd814f984e4a2384f; _Jo0OQK=54627D19E908EA9CF4A49A9DA15D0002092DCE1941734F47302D24D014602F685D086C862DF782CCE64D0293DF5F269BCFB1E3C9353138C83A66F454D9CBB01EC06C57212F12283777C98FB9E3C853EFEE298FB9E3C853EFEE215D8BEE34E43E5C0GJ1Z1bw==; HMY_JC=df2b9b15fd7dd0ddb03ff23099ca80393566922097a0d80f93e1ab91febd927515,; yfx_c_g_u_id_10000001=_ck21082909512414603753678571903; yfx_f_l_v_t_10000001=f_t_1630201884466__r_t_1630201884466__v_t_1630201884466__r_c_0; __TD_deviceId=HM0BUC8AU0LP2V7J; _gat=1; Hm_lpvt_94ed3d23572054a86ed341d64b267ec6=1630202053");
        String htmlStr = httpClient.get(url, null, map);
    }
}
