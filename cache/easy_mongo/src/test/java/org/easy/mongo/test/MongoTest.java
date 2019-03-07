package org.easy.mongo.test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.BsonType;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class MongoTest {

    private static final Logger LOGGER = LogManager.getLogger(MongoTest.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void getDBCollection() {
        String collectionName = "";
        MongoDatabase database =  mongoClient.getDatabase("")
        DBCollection dBCollection = mongoOperations.getCollection(collectionName);
    }

    public void printCollection(String doing, MongoCollection<Document> mc) {
        System.out.println(doing);
        FindIterable<Document> iterable = mc.find();
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        System.out.println("------------------------------------------------------");
        System.out.println();
    }

    // ------------------------------------共用方法---------------------------------------------------
    /**
     * 获取DB实例 - 指定DB
     *
     * @param dbName
     * @return
     */
    public MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * 获取collection对象 - 指定Collection
     *
     * @param collName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * 查询DB下的所有表名
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 获取所有数据库名称列表
     *
     * @return
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除一个数据库
     */
    public void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * 查找对象 - 根据主键_id
     *
     * @param collection
     * @param id
     * @return
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
        return myDoc;
    }

    /** 统计数 */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /** 条件查询 */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /** 分页查询 */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }


    /**
     * 通过ID删除
     *
     * @param coll
     * @param id
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * FIXME
     *
     * @param coll
     * @param id
     * @param newdoc
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idobj);
        // coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }

    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * 关闭Mongodb
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    /**
     * 测试入口
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) {

        String dbName = "test";
        String collName = "wd_paper_scie";
        MongoCollection<Document> coll = MongoUtil.instance.getCollection(dbName, collName);
        //coll.createIndex(new Document("validata",1));//创建索引
        //coll.createIndex(new Document("id",1));
        // coll.createIndex(new Document("ut_wos",1),new IndexOptions().unique(true));//创建唯一索引
        //coll.dropIndexes();//删除索引
        //coll.dropIndex("validata_1");//根据索引名删除某个索引
        ListIndexesIterable<Document> list = coll.listIndexes();//查询所有索引
        for (Document document : list) {
            System.out.println(document.toJson());
        }
        coll.find(Filters.and(Filters.eq("x", 1), Filters.lt("y", 3)));
        coll.find(and(eq("x", 1), lt("y", 3)));
        coll.find().sort(ascending("title"));
        coll.find().sort(new Document("id",1));
        coll.find(new Document("$or", Arrays.asList(new Document("owner", "tom"), new Document("words", new Document("$gt", 350)))));
        coll.find().projection(fields(include("title", "owner"), excludeId()));
        // coll.updateMany(Filters.eq("validata", 1), Updates.set("validata", 0));
        //coll.updateMany(Filters.eq("validata", 1), new Document("$unset", new Document("jigou", "")));//删除某个字段
        //coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliation", "affiliation_full")));//修改某个字段名
        //coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliationMeta", "affiliation")));
        //coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));//修改字段值
        //        MongoCursor<Document> cursor1 =coll.find(Filters.eq("ut_wos", "WOS:000382970200003")).iterator();
        //        while(cursor1.hasNext()){
        //            Document sd=cursor1.next();
        //            System.out.println(sd.toJson());
        //            coll.insertOne(sd);
        //        }

        //        MongoCursor<Document> cursor1 =coll.find(Filters.elemMatch("affInfo", Filters.eq("firstorg", 1))).iterator();
        //        while(cursor1.hasNext()){
        //            Document sd=cursor1.next();
        //            System.out.println(sd.toJson());
        //        }
        //查询只返回指定字段
        // MongoCursor<Document> doc= coll.find().projection(Projections.fields(Projections.include("ut_wos","affiliation"),Projections.excludeId())).iterator();
        //"ut_wos" : "WOS:000382970200003"
        //coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));
        //coll.updateMany(Filters.eq("validata", 0), new Document("$rename", new Document("sid", "ssid")), new UpdateOptions().upsert(false));
        //coll.updateOne(Filters.eq("ut_wos", "WOS:000382970200003"), new Document("$set", new Document("validata", 0)));
        //long isd=coll.count(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.eq("sid", 0))));
        // System.out.println(isd);
        //MongoCursor<Document> doc= coll.find(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.ne("sid", 0)))).projection(Projections.fields(Projections.elemMatch("affInfo"),Projections.excludeId())).iterator();
        //       MongoCursor<Document> doc= coll.find().projection(Projections.include("affInfo","ssid")).iterator();
        //       while(doc.hasNext()){
        //            Document sd=doc.next();
        //            System.out.println(sd.toJson());
        //
        //        }
        MongoUtil.instance.close();
        // 插入多条
        //         for (int i = 1; i <= 4; i++) {
        //         Document doc = new Document();
        //         doc.put("name", "zhoulf");
        //         doc.put("school", "NEFU" + i);
        //         Document interests = new Document();
        //         interests.put("game", "game" + i);
        //         interests.put("ball", "ball" + i);
        //         doc.put("interests", interests);
        //         coll.insertOne(doc);
        //         }
        //
        /* MongoCursor<Document> sd =coll.find().iterator();
         while(sd.hasNext()){
             Document doc = sd.next();
             String id =  doc.get("_id").toString();
             List<AffiliationJG> list = new ArrayList<AffiliationJG>();
             AffiliationJG jg = new AffiliationJG();
             jg.setAddress("123");
             jg.setCid(2);
             jg.setCname("eeee");
             jg.setSid(3);
             jg.setSname("rrrr");
             AffiliationJG jg2 = new AffiliationJG();
             jg2.setAddress("3242");
             jg2.setCid(2);
             jg2.setCname("ers");
             jg2.setSid(3);
             jg2.setSname("rasdr");
             list.add(jg);
             list.add(jg2);
             AffiliationList af = new AffiliationList();
             af.setAffiliationAuthos("33333");
             af.setAffiliationinfo("asdsa");
             af.setAffiliationJGList(list);
             JSONObject json = JSONObject.fromObject(af);
             doc.put("affInfo", json);
             MongoDBUtil.instance.updateById(coll, id, doc);
         }*/

        //        Bson bs = Filters.eq("name", "zhoulf");
        //        coll.find(bs).iterator();
        // // 根据ID查询
        // String id = "556925f34711371df0ddfd4b";
        // Document doc = MongoDBUtil2.instance.findById(coll, id);
        // System.out.println(doc);

        // 查询多个
        // MongoCursor<Document> cursor1 = coll.find(Filters.eq("name", "zhoulf")).iterator();
        // while (cursor1.hasNext()) {
        // org.bson.Document _doc = (Document) cursor1.next();
        // System.out.println(_doc.toString());
        // }
        // cursor1.close();

        // 查询多个
        //         MongoCursor<WdPaper> cursor2 = coll.find(WdPaper.class).iterator();
        //         while(cursor2.hasNext()){
        //             WdPaper doc = cursor2.next();
        //             System.out.println(doc.getUt_wos());
        //         }
        // 删除数据库
        // MongoDBUtil2.instance.dropDB("testdb");

        // 删除表
        // MongoDBUtil2.instance.dropCollection(dbName, collName);

        // 修改数据
        // String id = "556949504711371c60601b5a";
        // Document newdoc = new Document();
        // newdoc.put("name", "时候");
        // MongoDBUtil.instance.updateById(coll, id, newdoc);

        // 统计表
        //System.out.println(MongoDBUtil.instance.getCount(coll));

        // 查询所有
        //        Bson filter = Filters.eq("count", 0);
        //        MongoDBUtil.instance.find(coll, filter);

    }
}
