package com.easy.utils;

/**
 * @author zhouym
 * @version [1.0, 2018/2/28]
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MideaRestResponse<T> implements Serializable {
    public static final String REST_RESPONSE_SUCCESS_CODE = "0";
    public static final String REST_RESPONSE_FIAL_CODE = "-1";
    public static final String REST_SERVER_RESPONSE_FIAL_CODE = "1";
    public static final String REST_TEMPLATE_RESPONSE_FIAL_CODE = "2";

    private String requestId;

    @JSONField(
            name = "resultCode"
    )
    @JsonProperty("resultCode")
    private String resultCode;
    @JSONField(
            name = "message"
    )
    @JsonProperty("message")
    private String message;
    @JSONField(
            name = "resultData"
    )
    @JsonProperty("resultData")
    private T resultData;


    public MideaRestResponse() {
    }

    public MideaRestResponse(String code) {
        this.resultCode = code;
    }

    public MideaRestResponse(String code, String msg) {
        this.resultCode = code;
        this.message = msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return "0".equals(this.resultCode);
    }

    @Override
    public String toString() {
        return "MideaRestResponse{" +
                "requestId='" + requestId + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}

