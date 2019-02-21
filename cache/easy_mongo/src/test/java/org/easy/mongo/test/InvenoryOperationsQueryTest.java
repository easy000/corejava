package org.easy.mongo.test;

import org.easy.mongo.entity.Instock;
import org.easy.mongo.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class InvenoryOperationsQueryTest {

    private static final Logger LOGGER = LogManager.getLogger(InvenoryOperationsQueryTest.class);

    @Autowired
    private MongoOperations mongoOperations;

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
//        org.apache.logging.log4j.web.Log4jServletContextListener
        //db.inventory.find( { "instock": { warehouse: "A", qty: 5 } } )
        //# 查询含有 instock array中的json元素为{ warehouse: "A", qty: 5 } 的数据

        String warehouse = "A";
        Integer qty = 5;
        Instock instock = new Instock();
        instock.setQty(qty);
        instock.setWarehouse(warehouse);
        Query query = new Query();
//        query.addCriteria( new Criteria().and("instock").is(instock));
////                Criteria.where("warehouse").is(warehouse),
////                Criteria.where("qty").is(qty)));
//        List<Inventory> list = mongoOperations.find(query, Inventory.class, "inventory");
//        LOGGER.error("==========================");
//        printLog(list);
//
//        query = new Query();
//        query.addCriteria( new Criteria().andOperator(
//                Criteria.where("item").is("journal")));
        List<String > tags = new ArrayList<>();
        tags.add("blank");
        tags.add("red");
        query.addCriteria(Criteria.where("tags").all(tags));
        printLog( mongoOperations.find(query, Inventory.class));
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

    public static void printLog(List<? extends Object> list) {
        for(Object obj : list) {
            System.out.println(obj);
        }
    }
}
