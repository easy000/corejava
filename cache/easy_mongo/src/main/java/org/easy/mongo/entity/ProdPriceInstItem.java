package org.easy.mongo.entity;

/**
 * @ClassName:  ProdPriceInstItem   
 * @Description:时段定价信息   
 * @author: zhaoqing
 * @date:   2019年5月8日 下午6:29:14
 */
public class ProdPriceInstItem {
	
	/**
	 * 定价配置实例ID
	 */
	private Integer prodPriceInstId;
	
	/**
	 * 定价配置ID
	 */
	private Integer prodPriceId;
	
	/**
	 * 配套时段名称
	 */
	private String serviceName;
	
	/**
	 * 服务开始时段
	 */
	private Integer serviceStartTime;
	
	/**
	 * 服务结束时段
	 */
	private Integer serviceEndTime;
	
	/**
	 * 公司内部价格
	 */
	private Integer innerPrice;
	
	/**
	 * 外部价格
	 */
	private Integer outerPrice;
	
	/**
	 * 服务客户价格
	 */
	private Integer servicePrice;
	
	/**
	 * 原价
	 */
	private Integer originalPrice;
	
	/**
	 * 状态：0：不开放 1：正常 2：微调 3：已预订 4:日期不开放 
	 * 对应BookingScheduleStatusEnum枚举类中的状态
	 */
	private String status;

	public Integer getProdPriceInstId() {
		return prodPriceInstId;
	}

	public void setProdPriceInstId(Integer prodPriceInstId) {
		this.prodPriceInstId = prodPriceInstId;
	}

	public Integer getProdPriceId() {
		return prodPriceId;
	}

	public void setProdPriceId(Integer prodPriceId) {
		this.prodPriceId = prodPriceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(Integer serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public Integer getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(Integer serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	public Integer getInnerPrice() {
		return innerPrice;
	}

	public void setInnerPrice(Integer innerPrice) {
		this.innerPrice = innerPrice;
	}

	public Integer getOuterPrice() {
		return outerPrice;
	}

	public void setOuterPrice(Integer outerPrice) {
		this.outerPrice = outerPrice;
	}

	public Integer getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProdPriceInstItem [prodPriceInstId=" + prodPriceInstId
				+ ", prodPriceId=" + prodPriceId + ", serviceName="
				+ serviceName + ", serviceStartTime=" + serviceStartTime
				+ ", serviceEndTime=" + serviceEndTime + ", innerPrice="
				+ innerPrice + ", outerPrice=" + outerPrice + ", servicePrice="
				+ servicePrice + ", originalPrice=" + originalPrice
				+ ", status=" + status + "]";
	}
	
}
