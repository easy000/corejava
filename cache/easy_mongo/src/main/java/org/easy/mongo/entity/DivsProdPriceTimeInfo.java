package org.easy.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:  DivsProdPriceTimeInfo   
 * @Description:时段定价信息   
 * @author: zhaoqing
 * @date:   2019年5月8日 下午6:15:13
 */
@Document(collection = "divs_prod_price_time_info")
public class DivsProdPriceTimeInfo {
	
	/**
     * @ID 生成MongoDB文档的_id
     */
    @Id
    private String id;
    
    /**
     * @Field 一级物业Id
     */
    private Integer groupOrganId;

    /**
     * @Field 组织机构Id
     */
    @Field("organId")
    private Integer organId;
    
    /**
     * @Field 资源Id
     */
    @Field("resourceId")
    private Integer resourceId;
    
    /**
     * @Field 排程月份（格式：YYYYMM）
     */
    @Field("scheduleMonth")
    private Integer scheduleMonth;
    
    /**
     * @Field 开放日期（格式：YYYY-MM-DD）
     */
    @Field("openDate")
    private String openDate;
    
    /**
     * @Field 时段类型(1:按小时 2：按半天 3：按全天)
     */
    @Field("serviceTimeType")
    private String serviceTimeType;
    
    /**
     * @Field 一天的时段定价信息列表
     */
    private List<?> prodPriceInstList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getScheduleMonth() {
		return scheduleMonth;
	}

	public void setScheduleMonth(Integer scheduleMonth) {
		this.scheduleMonth = scheduleMonth;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getServiceTimeType() {
		return serviceTimeType;
	}

	public void setServiceTimeType(String serviceTimeType) {
		this.serviceTimeType = serviceTimeType;
	}

	public List<?> getProdPriceInstList() {
		return prodPriceInstList;
	}

	public void setProdPriceInstList(List<?> prodPriceInstList) {
		this.prodPriceInstList = prodPriceInstList;
	}

	@Override
	public String toString() {
		return "DivsProdPriceTimeInfo [id=" + id + ", groupOrganId="
				+ groupOrganId + ", organId=" + organId + ", resourceId="
				+ resourceId + ", scheduleMonth=" + scheduleMonth
				+ ", openDate=" + openDate + ", serviceTimeType="
				+ serviceTimeType + ", prodPriceInstList=" + prodPriceInstList
				+ "]";
	}

}
