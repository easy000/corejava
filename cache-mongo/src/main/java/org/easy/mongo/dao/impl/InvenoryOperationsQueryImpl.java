package org.easy.mongo.dao.impl;

import org.easy.mongo.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

public class InvenoryOperationsQueryImpl {
    @Autowired
    private MongoOperations mongoOperations;

//    and和范围条件查询：
//            db.inventory.find( { status: "A", qty: { $lt: 30 } } )
    public List<Inventory> queryInventoryAnd(String status, Integer qty) {

        return
    }
//    or条件查询：
//            db.inventory.find( { $or: [ { status: "A" }, { qty: { $lt: 30 } } ] } )
//    and和or的复合条件：
//            db.inventory.find( {status: "A",   $or: [ { qty: { $lt: 30 } }, { item: /^p/ } ]})
//    查询一条：db.collection.findOne()
}
