package org.easy.mongo.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easy.mongo.entity.DivsProdPriceTimeInfo;
import org.easy.mongo.entity.Instock;
import org.easy.mongo.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 包:       org.easy.mongo.test
 * 类名称:    ListUpdateTest
 * 类描述:
 * 创建人:    wangxiong
 * 创建时间:  2019/5/15 16:49
 * 修改人:    Administrator
 * 修改时间:  2019/5/15 16:49
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ListUpdateTest {

    private static final Logger LOGGER = LogManager.getLogger(InvenoryOperationsQueryTest.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testArrayJson() {
//        org.apache.logging.log4j.web.Log4jServletContextListener
        //db.inventory.find( { "instock": { warehouse: "A", qty: 5 } } )
        //# 查询含有 instock array中的json元素为{ warehouse: "A", qty: 5 } 的数据
        Query query = new Query();
        query.addCriteria( new Criteria().andOperator(
                Criteria.where("openDate").is("2019-05-14"),
                Criteria.where("prodPriceInstList").elemMatch(Criteria.where("prodPriceId").is(10314))));
        query.fields().include("prodPriceInstList.$");
        List<DivsProdPriceTimeInfo> list = mongoOperations.find(query, DivsProdPriceTimeInfo.class, "divs_prod_price_time_info");
        LOGGER.error("==========================");
        printLog(list);
    }
    public static void printLog(List<? extends Object> list) {
        for(Object obj : list) {
            LOGGER.error(obj);
        }
    }
}
