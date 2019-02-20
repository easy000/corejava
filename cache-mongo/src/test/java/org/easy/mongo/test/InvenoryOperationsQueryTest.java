package org.easy.mongo.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class InvenoryOperationsQueryTest {


    @Autowired
    private MongoOperations mongoOperations;

//    public List<Inventory> queryArray() {
//
//        return null;
//    }

//    public List<Inventory> queryArrayElemMatch(String status, Integer qty) {
//        Query query = new Query();
//        query.addCriteria( new Criteria().orOperator(
//                Criteria.where("status").elemMatch(
//                        Criteria.where("day").is(day),
//                        Criteria.where("day").is(day)),
//                Criteria.where("qty").lt(qty)));
//        return mongoOperations.find(query, Inventory.class);
//    }

//db.inventory.find( { "instock": { qty: 5, warehouse: "A" } } ) # 查询含有 instock array中的json元素为{qty: 5, warehouse: "A" } 的数据查不到 （json中的数据一体化，位置不可移动）
//db.inventory.find( { 'instock.0.qty': { $lte: 20 } } ) # 查询含有 instock中的第一个json元素中的qty字段小于或等于 20 的数据
//db.inventory.find( { 'instock.qty': { $lte: 20 } } )  # 查询含有 instock中的任意json元素中的qty小于或等于 20 的数据
//db.inventory.find( { "instock": { $elemMatch: { qty: 5, warehouse: "A" } } } )  # 查询含有  instock中的json元素 同时满足qty为5，warehouse为A  的数据
//db.inventory.find( { "instock": { $elemMatch: { qty: { $gt: 10, $lte: 20 } } } } )  # 查询含有 instock中的json元素 同时满足qty大于10并小于等于20 的数据
//--- 如果不用 $elemMatch: 函数
//db.inventory.find( { "instock.qty": 5, "instock.warehouse": "A" } )# 查询含有 instock中的qty为5 或者 instock中的warehousr为A 的数据
//db.inventory.find( { "instock.qty": { $gt: 10,  $lte: 20 } } ) # 查询含有 instock中的qty 大于10 或 小于等于20 的数据
    @Test
    public void testArrayJson() {
        //db.inventory.find( { "instock": { warehouse: "A", qty: 5 } } )
        //# 查询含有 instock array中的json元素为{ warehouse: "A", qty: 5 } 的数据


    }

    @Test
    public void testArrayJson1() {
        //db.inventory.find( { 'instock.0.qty': { $lte: 20 } } )
        // # 查询含有 instock中的第一个json元素中的qty字段小于或等于 20 的数据



    }

    @Test
    public void testArrayJson2() {
        //db.inventory.find( { 'instock.qty': { $lte: 20 } } )
        // # 查询含有 instock中的任意json元素中的qty小于或等于 20 的数据




    }

    @Test
    public void testArrayJson3() {
        //db.inventory.find( { "instock": { $elemMatch: { qty: 5, warehouse: "A" } } } )
        // # 查询含有  instock中的json元素 同时满足qty为5，warehouse为A  的数据

        //db.inventory.find( { "instock.qty": 5, "instock.warehouse": "A" } )
        // # 查询含有 instock中的qty为5 或者 instock中的warehousr为A 的数据
    }

    @Test
    public void testArrayJson4() {
        //db.inventory.find( { "instock": { $elemMatch: { qty: { $gt: 10, $lte: 20 } } } } )
        // # 查询含有 instock中的json元素 同时满足qty大于10并小于等于20 的数据

        //db.inventory.find( { "instock.qty": { $gt: 10,  $lte: 20 } } )
        // # 查询含有 instock中的qty 大于10 或 小于等于20 的数据

    }


}
