package com.easy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jackzhong on 9/5/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDto<T>  {

    /**
     * 请求唯一标识
     */
    @NotBlank(message = "requestId不能为空")
    private String requestId;

    /**
     * 请求时间
     */
    @NotBlank(message = "requestTime不能为空")
    private String requestTime;

    /**
     * 随机字符串
     */
    @NotBlank(message = "noncestr不能为空")
    private String noncestr;

    /**
     * 应用Key
     */
    @NotBlank(message = "appKey不能为空")
    private String appKey;
    @NotBlank(message = "apiVersion不能为空")
    private String apiVersion;
    private T      data;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
