package org.easy.mongo.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;//要记得import

//https://www.jb51.net/article/151854.htm
//https://blog.csdn.net/u011113654/article/details/80353013
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class InvenoryOperationsLookUpTest {
    private static final Logger LOGGER = LogManager.getLogger(InvenoryOperationsQueryTest.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void test() {
        //设置lookup
        LookupOperation lookupOperation = LookupOperation.newLookup().from("user").localField("userId").foreignField("_id").as("userDoc");

        //这里分三个部分，先lookup；结果再用project筛选，如果不筛选user里面所有内容，包括密码都会输出；最后sort排序
        Aggregation aggregation = newAggregation(
                lookupOperation,
                project("id", "demographic", "sizes", "userDoc.realname"),
                sort(Sort.Direction.ASC, "demographic"));
        ///////////////////
        LOGGER.debug(aggregation.toString());//看具体的查询，有助于理解各个参数的影响

        //正式查询
        List<OrderQsDemographicSize> list = MongoTemplate
                .aggregate(aggregation, "orderQsDemographicSize", OrderQsDemographicSize.class).getMappedResults();
    }
}
