package com.mk.diy.bigbigweb.model.request;

/**
 * 关注/取消关注 事件
 *
 * @author wanghao
 * @create 2017-10-15 12:40
 */
public class LocationEvent extends EventBase {
    private static final long serialVersionUID = 2119142076701131832L;
    private Double latitude;
    private Double longitude;
    private Double precision;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
