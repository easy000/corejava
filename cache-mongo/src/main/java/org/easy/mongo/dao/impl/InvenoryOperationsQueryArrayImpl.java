package org.easy.mongo.dao.impl;

import org.easy.mongo.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class InvenoryOperationsQueryArrayImpl {
    @Autowired
    private MongoOperations mongoOperations;

    public List<Inventory> queryArray() {

        return null;
    }

    public List<Inventory> queryArrayElemMatch(String status, Integer qty) {
        Query query = new Query();
        query.addCriteria( new Criteria().orOperator(
                Criteria.where("status").elemMatch(Criteria.where("day").is(day)),
                Criteria.where("qty").lt(qty)));
        return mongoOperations.find(query, Inventory.class);
        Criteria
        Criteria.where("flowList").elemMatch(Criteria.where("day").is(day));
        return null;
    }
}