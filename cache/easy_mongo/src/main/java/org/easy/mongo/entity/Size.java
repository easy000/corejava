package org.easy.mongo.entity;

public class Size {
    private Integer h;
    private Integer w;
    private Integer uom;

    public Size(Integer h, Integer w, Integer uom) {
        this.h = h;
        this.w = w;
        this.uom = uom;
    }

    public Size() {
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getUom() {
        return uom;
    }

    public void setUom(Integer uom) {
        this.uom = uom;
    }
}
