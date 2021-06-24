package com.qiusm.eju.crawler.government.nj.entity;

public class FdNanJinHouseWithBLOBs extends FdNanJinHouse {
    private String xgPictureUrl;

    private String layoutPictureUrl;

    public String getXgPictureUrl() {
        return xgPictureUrl;
    }

    public void setXgPictureUrl(String xgPictureUrl) {
        this.xgPictureUrl = xgPictureUrl == null ? null : xgPictureUrl.trim();
    }

    public String getLayoutPictureUrl() {
        return layoutPictureUrl;
    }

    public void setLayoutPictureUrl(String layoutPictureUrl) {
        this.layoutPictureUrl = layoutPictureUrl == null ? null : layoutPictureUrl.trim();
    }
}