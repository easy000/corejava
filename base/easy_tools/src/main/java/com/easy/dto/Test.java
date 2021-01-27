package com.easy.dto;

import com.alibaba.fastjson.JSON;
import com.easy.utils.MideaCommonUtils;
import com.easy.utils.SignatureUtils;
import com.easy.utils.ValidationUtil;


import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args ) {
        Map params = JSON.parseObject("{\"apiVersion\":\"0.1\",\"appKey\":\"a48f90ff6c6860f79560dcde5775513d\",\"data\":[{\"carNo\":\"粤BC96T7\",\"carParkingCode\":\"sz3501\",\"carParkingId\":\"\",\"communityId\":\"\",\"custId\":\"-1\",\"finalMoney\":\"600\",\"inTime\":\"2020-12-23 08:54:03\",\"money\":\"600\",\"offset\":\"0\",\"outTime\":\"2020-12-23 10:15:39\",\"payType\":\"2\",\"stayTime\":\"81\"}],\"noncestr\":\"ee08456eebd64c8aa3f3ae4fd947a03b\",\"requestId\":\"8331d407d8334784aa3ca1f2ba3017ff\",\"requestTime\":\"1609983846777\"}", Map.class);
        BaseDto<List<Map>> baseDto = (BaseDto) MideaCommonUtils.mapToObject(params, BaseDto.class);
//        ValidationUtil.getInstance().validateParams(baseDto);
        long time = Long.parseLong(baseDto.getRequestTime());
        if (System.currentTimeMillis() - time > 3600000) {
        }
        String str = JSON.toJSONString(baseDto);
        String mySignature = SignatureUtils.signature(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0lQHUD2cpGHtsvka9B5EbaAneoR/TY+MdbPMjQxNttuANme2tLQ2d1Mfx6aVJqvXm0DrmvW1C8HKhDOxpD36/5GqAhg+BLIQtZJz45CEmNnQ8DYG495+wGI8aOEaPSTI2jNtVupdoycMSk1m2rCWhPAyndeA0EW4l4X5XFI5atQIDAQAB");
//        logger.info("mySignature:{},signature:{}",mySignature,signature);
        System.out.println(mySignature);
    }
}