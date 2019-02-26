package org.easy.mongo.entity;

import java.util.List;

/**
 * 包:       org.easy.mongo.entity
 * 类名称:    Order
 * 类描述:
 * 创建人:    wangxiong
 * 创建时间:  2019/2/26 11:55
 * 修改人:    Administrator
 * 修改时间:  2019/2/26 11:55
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */
public class Order {
//db.orders.insert([
//   { "_id" : 1, "item" : "almonds", "price" : 12, "quantity" : 2 },
    private String item ;
    private Integer price;
    private Integer quantity;

    private List<doc> docs;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", docs=" + docs +
                '}';
    }

    class doc {
        private String sku;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        @Override
        public String toString() {
            return "doc{" +
                    "sku='" + sku + '\'' +
                    '}';
        }
    }
}
