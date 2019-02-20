package org.easy.mongo.dao.impl;

import org.easy.mongo.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class InvenoryOperationsQueryImpl {
    @Autowired
    private MongoOperations mongoOperations;

//    and和范围条件查询：
//            db.inventory.find( { status: "A", qty: { $lt: 30 } } )
    public List<Inventory> queryInventoryAnd(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria( new Criteria().andOperator(
                Criteria.where("status").is(status),
                Criteria.where("qty").lt(qty)));
        return mongoOperations.find(query, Inventory.class);
    }
//    or条件查询：
//            db.inventory.find( { $or: [ { status: "A" }, { qty: { $lt: 30 } } ] } )
    public List<Inventory> queryInventoryOr(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria( new Criteria().orOperator(
                Criteria.where("status").is(status),
                Criteria.where("qty").lt(qty)));
        return mongoOperations.find(query, Inventory.class);
    }

//    and和or的复合条件：
//            db.inventory.find( {status: "A",   $or: [ { qty: { $lt: 30 } }, { item: /^p/ } ]})
    public List<Inventory> queryInventoryOrAnd(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("status").is(status),
                        new Criteria().andOperator(
                                Criteria.where("qty").lt(qty),
                                Criteria.where("item").regex("/^p/")) ) );
        return mongoOperations.find(query, Inventory.class);
    }

//    查询一条：db.collection.findOne()
    public Inventory queryInventoryFindOne(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria( new Criteria().orOperator(
                Criteria.where("status").is(status),
                Criteria.where("qty").lt(qty)));
        return mongoOperations.findOne(query, Inventory.class);
    }

//    db.inventory.find( { size: { h: 14, w: 21, uom: "cm" } } )能查到
    public List<Inventory> queryInventoryJson(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("status").is(status),
                        new Criteria().andOperator(
                                Criteria.where("qty").lt(qty) ) ) );
        return mongoOperations.find(query, Inventory.class);
    }
//db.inventory.find(  { size: { w: 21, h: 14, uom: "cm" } }  )查不到 （json中的数据一体化，位置不可移动）

//            db.inventory.find( { "size.uom": "in" } ) 　　　　# 执行条件的查询

//db.inventory.find( { "size.h": { $lt: 15 } } )  # 范围条件的查询

//db.inventory.find( { "size.h": { $lt: 15 }, "size.uom": "in", status: "D" } ) #and条件的查询

    /**
     * 通过条件进行分页查询
     * @param query        查询条件
     * @param start        查询起始值
     *                     <strong> 类似mysql查询中的 limit start, size 中的 start</strong>
     * @param size         查询大小
     *                     <strong> 类似mysql查询中的 limit start, size 中的 size</strong>
     * @return             满足条件的集合
     */
    public List<Inventory> getPage(Query query, int start, int size){
        query.skip(start);
        query.limit(size);
        List<Inventory> lists = this.mongoOperations.find(query, Inventory.class);
        return lists;
    }

    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     * @param query     查询条件
     * @return          满足条件的记录总数
     */
    public Long getPageCount(Query query) {
        return this.mongoOperations.count(query, Inventory.class);
    }


}
