//package org.easy.mongo.dao.impl;
//
//import com.segi.uhomecp.medicaltrans.mongodb.deptvol.dao.DeptVolOperations;
//import com.segi.uhomecp.medicaltrans.mongodb.deptvol.entity.MtDeptVolumeTopTen;
//import com.segi.uhomecp.medicaltrans.utils.DateCommonUtil;
//import com.segi.uhomecp.wh.common.utils.AppUtils;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Pattern;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DeptVolOperationsImpl
//  implements DeptVolOperations
//{
//  @Autowired
//  private MongoOperations mongoOperations;
//
//  public void updateDeptVolumeTopTenList(List<MtDeptVolumeTopTen> list)
//  {
//    if (AppUtils.isNotEmpty(list)) {
//      for (MtDeptVolumeTopTen mtDeptVolumeTopTen : list) {
//        saveDeptVolumeTopTen(mtDeptVolumeTopTen);
//      }
//    }
//  }
//
//  private void saveDeptVolumeTopTen(MtDeptVolumeTopTen entity)
//  {
//    Date nowDate = new Date();
//
//    String monthDay = DateCommonUtil.getMonthDay(nowDate);
//
//    String id = monthDay + entity.getHouseId();
//    entity.setId(id);
//    entity.setCreateDate(nowDate);
//    this.mongoOperations.save(entity);
//  }
//
//  public List<MtDeptVolumeTopTen> queryDeptVolumeTopTen(int organId)
//  {
//    String monthDay = DateCommonUtil.getMonthDay(new Date());
//
//    Pattern pattern = Pattern.compile("^" + monthDay + ".*$", 2);
//    Criteria criteria = new Criteria();
//    criteria.and("organId").is(Integer.valueOf(organId));
//
//    criteria.and("_id").regex(pattern);
//    Query query = new Query(criteria);
//    query.with(new Sort(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, "transAmount") }));
//    query.limit(10);
//    List<MtDeptVolumeTopTen> resultList = this.mongoOperations.find(query, MtDeptVolumeTopTen.class);
//
//    filerMtDeptVolumeTopTen(resultList);
//
//    resultList = sortMtDeptVolumeTopTenListAsc(resultList);
//    return resultList;
//  }
//
//  private List<MtDeptVolumeTopTen> sortMtDeptVolumeTopTenListAsc(List<MtDeptVolumeTopTen> list)
//  {
//    List<MtDeptVolumeTopTen> resultList = new ArrayList();
//    if (!AppUtils.isNotEmpty(list)) {
//      return resultList;
//    }
//    int size = list.size();
//    for (int i = size - 1; i >= 0; i--) {
//      resultList.add(list.get(i));
//    }
//    return resultList;
//  }
//
//  private void filerMtDeptVolumeTopTen(List<MtDeptVolumeTopTen> resultList)
//  {
//    List<MtDeptVolumeTopTen> deleteList = new ArrayList();
//    if (AppUtils.isNotEmpty(resultList)) {
//      for (MtDeptVolumeTopTen mtDeptVolumeTopTen : resultList) {
//        if (mtDeptVolumeTopTen.getTransAmount().intValue() <= 0) {
//          deleteList.add(mtDeptVolumeTopTen);
//        }
//      }
//    }
//    if (AppUtils.isNotEmpty(deleteList)) {
//      resultList.removeAll(deleteList);
//    }
//  }
//
//  public void deleteDeptVolumeTopTenList(int organId)
//  {
//    String monthDay = DateCommonUtil.getMonthDay(new Date());
//
//    Pattern pattern = Pattern.compile("^" + monthDay + ".*$", 2);
//    Criteria criteria = new Criteria();
//    criteria.and("organId").is(Integer.valueOf(organId));
//
//    criteria.and("_id").regex(pattern);
//    Query query = new Query(criteria);
//    this.mongoOperations.remove(query, MtDeptVolumeTopTen.class);
//  }
//}
