package com.qiusm.eju.crawler.service.bk;

public interface ISecondHandHouseService {

    /**
     * 抓取二手房挂牌列表 <br>
     * 数据写在文件中，目录是【source/bk】下 <br>
     *
     * @param cityId bk城市6位编码
     * @param cityPy bk城市拼音简写
     */
    void handler(String cityId, String cityPy);
}
