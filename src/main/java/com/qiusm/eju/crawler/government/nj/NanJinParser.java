package com.qiusm.eju.crawler.government.nj;

import java.util.List;
import java.util.Map;

/**
 * 南京政府源头html解析接口
 *
 * @author qiushengming
 */
public interface NanJinParser {
    /**
     * 首页总页码解析
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @return 总页码
     */
    Integer totalPageNumFromHomepageParser(String requestUrl, String htmlStr);

    /**
     * 项目列表页解析
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @return 楼盘项目列表
     */
    List<Map<String, Object>> xmListNowPageParser(String requestUrl, String htmlStr);

    /**
     * 楼盘首页解析
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @return 楼盘项目的详细信息，包含预售证地址；
     */
    Map<String, Object> xmDetailParser(String requestUrl, String htmlStr);

    /**
     * 开发商详情解析
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @return 开发商信息
     */
    Map<String, Object> xmKfsDetailsParser(String requestUrl, String htmlStr);

    /**
     * 预售证明细解析
     *
     * @param requestUrl 请求的URL
     * @param htmlStr    响应的HTML结果
     * @return 预售证信息
     */
    Map<String, Object> xmPreSaleDetailsParser(String requestUrl, String htmlStr);

    /**
     * 从出售公示的请求结果中解析出来楼栋列表 <br>
     * 公示明细解析 <br>
     * 主要获取物价申报价格表的URL（是个图片），以及楼栋详细信息的URL. <br>
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 返回楼栋列表
     */
    Map<String, Object> xmSalesDetailsParser(String requestUrl, String htmlStr);

    /**
     * 从楼栋的请求结果中解析出来房屋列表
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 返回房屋列表
     */
    Map<String, Object> xmBuildingDetailsParser(String requestUrl, String htmlStr);

    /**
     * 房屋明细信息解析
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 返回房屋明细
     */
    Map<String, Object> xmUnitDetailsParser(String requestUrl, String htmlStr);

    /**
     * 鸟瞰图解析
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 鸟瞰图的绝对路径
     */
    Map<String, Object> xmNiaokPictureParser(String requestUrl, String htmlStr);

    /**
     * 区位图解析
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 区位图的绝对路径
     */
    Map<String, Object> xmQwPictureParser(String requestUrl, String htmlStr);

    /**
     * 效果图解析
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 效果图的绝对路径
     */
    List<String> xmXgPictureParser(String requestUrl, String htmlStr);

    /**
     * 户型图解析
     *
     * @param requestUrl 请求URL
     * @param htmlStr    响应结果
     * @return 户型图的绝对路径
     */
    List<String> xmLayoutPictureParser(String requestUrl, String htmlStr);
}
