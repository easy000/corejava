package org.easy.mongo.entity;

import java.util.List;

public class Inventory {
    private String item;
    private Integer qty;
    private Size size;
    private String status;
    private List<String> tags;
    private List<Integer> dimCm;
//    { item: "journal", instock: [ { warehouse: "A", qty: 5 }, { warehouse: "C", qty: 15 } ] },
//    { item: "notebook", instock: [ { warehouse: "C", qty: 5 } ] },
//    { item: "paper", instock: [ { warehouse: "A", qty: 60 }, { warehouse: "B", qty: 15 } ] },
//    { item: "planner", instock: [ { warehouse: "A", qty: 40 }, { warehouse: "B", qty: 5 } ] },
//    { item: "postcard", instock: [ { warehouse: "B", qty: 15 }, { warehouse: "C", qty: 35 } ] }


    public Inventory(String item, Integer qty, Size size, String status, List<String> tags, List<Integer> dimCm) {
        this.item = item;
        this.qty = qty;
        this.size = size;
        this.status = status;
        this.tags = tags;
        this.dimCm = dimCm;
    }

    public Inventory() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Integer> getDimCm() {
        return dimCm;
    }

    public void setDimCm(List<Integer> dimCm) {
        this.dimCm = dimCm;
    }
}