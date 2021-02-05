package com.easy.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        String json = "{\"code\":\"0\",\"data\":{\"code\":\"0\",\"data\":{\"attributes\":{},\"dataItems\":[{\"attributes\":{\"areaId\":\"88d4357fe8ae4f4e907c09e75a9e4850\",\"identityCode\":\"\",\"personCode\":\"001360\",\"personId\":\"9efb2ca32993422cb6bf7b62433e058c\",\"personName\":\"guojie\",\"telephone\":\"13714147774\"},\"failItems\":[],\"objectId\":\"PERSON\",\"operateType\":\"READ\",\"subItems\":[{\"attributes\":{\"carNo\":\"粤-B1111\",\"cardTypeId\":\"e49b4f06b2ac11ea99d16c4b90a8fce6\",\"cardTypeName\":\"月租用户C(TEST)\",\"chargeStandard\":[{\"cardTypeName\":\"月租用户C(TEST)\",\"monthPeriod\":1,\"periodMoney\":300}],\"delayMode\":\"1\",\"endTime\":\"2021-03-28 00:00:00\",\"locationCount\":\"1\",\"parkCode\":\"p200319301\",\"parkName\":\"联建科技工业园停车场\",\"serviceCode\":\"21012709245752898141\",\"serviceId\":\"fd2f9bd7ec6e4d20a3302fae2574adc8\",\"startTime\":\"2021-01-27 00:00:00\",\"status\":\"NORMAL\"},\"failItems\":[],\"objectId\":\"SERVICE\",\"operateType\":\"READ\",\"subItems\":[]}]}],\"message\":\"查询成功\",\"resultCode\":0,\"seqId\":\"-tezjsz_3264qv\",\"serviceId\":\"3c.card.querydelaylistbycarno\"},\"message\":\"SUCCCESS\",\"requestId\":\"20200119112013756\",\"responseTime\":\"2021-01-27 09:47:54483\",\"simulation\":\"mock\"},\"message\":\"SUCCCESS\",\"requestId\":\"20210201173014868\",\"responseTime\":\"2021-02-01 17:30:15233\",\"systemCode\":\"JIEHUITONG\"}";
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
        JSONObject jsonObject2 = (JSONObject)jsonObject1.get("data");
        JSONArray dataItems = jsonObject2.getJSONArray("dataItems");
        if (null == dataItems || dataItems.size() == 0) {
            System.out.println("1111111");
        }

        // 一个车牌只会绑定一张月卡，服务数组中只会有一条服务数据
        JSONObject dataItem = dataItems.getJSONObject(0);
        // 月卡绑定的用户信息
        JSONObject personInfo = dataItem.getJSONObject("attributes");
        // 月卡下的车牌服务信息
        JSONArray subItems = dataItem.getJSONArray("subItems");

        System.out.println(JSON.toJSONString(subItems));
        if (null == subItems || subItems.size() == 0) {
            System.out.println("2222222");
        }
    }
}
