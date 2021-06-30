package com.qiusm.eju.crawler.competitor.beike.entity;

public class BkFence {
    private Long id;

    private String cityCode;

    private String cityName;

    private String district;

    private String bizcircle;

    private String longitude;

    private String latitude;

    private String type;

    private String version;

    private String fence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getBizcircle() {
        return bizcircle;
    }

    public void setBizcircle(String bizcircle) {
        this.bizcircle = bizcircle == null ? null : bizcircle.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getFence() {
        return fence;
    }

    public void setFence(String fence) {
        this.fence = fence == null ? null : fence.trim();
    }
}