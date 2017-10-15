package com.mk.diy.bigbigweb.model.request;

/**
 * 地理位置信息
 *
 * @author wanghao
 * @create 2017-10-15 12:28
 */
public class LocationMsg extends MsgBase {
    private static final long serialVersionUID = 7134696693274917225L;

    private Double location_X;
    private Double location_Y;
    private Integer scale;
    private String label;

    public Double getLocation_X() {
        return location_X;
    }

    public void setLocation_X(Double location_X) {
        this.location_X = location_X;
    }

    public Double getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(Double location_Y) {
        this.location_Y = location_Y;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
