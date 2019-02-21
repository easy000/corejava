package org.easy.mongo.entity;

public class Instock {
    private String warehouse;
    private Integer qty;

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Instock(String warehouse, Integer qty) {
        this.warehouse = warehouse;
        this.qty = qty;
    }

    public Instock() {
    }


}
