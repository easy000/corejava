package com.easy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easy.dto.BaseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackzhong on 2015/12/26.
 */
public final class SignatureUtils {

    //日志
    public static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    public static final String CHARSET = "UTF-8";
    public static final String MD5 = "MD5";

    public static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();



    /**
     * 生成签名,传到http头部
     * @param baseDto
     * @param apiSecret
     * @return
     */
    public static String getSignature(BaseDto baseDto, String apiSecret) {
        String str = JSON.toJSONString(baseDto);
        return SignatureUtils.signature(str, apiSecret);
    }

    /**
     * 参数封装，传到body
     * @param appKey
     * @param data
     * @return
     */
    public static BaseDto init(String appKey,Object data)
    {
        BaseDto baseDto = new BaseDto();
        baseDto.setRequestId(MideaCommonUtils.createUniqueId());
        baseDto.setApiVersion("1.0");
        baseDto.setAppKey(appKey);
        baseDto.setNoncestr(MideaCommonUtils.createUniqueId());
        baseDto.setRequestTime(System.currentTimeMillis()+"");
        baseDto.setData(data);
        return baseDto;
    }




    public static byte[] toBytes(String string) {
        try {
            return string.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not supported by platform", e);
        }
    }

    public static String encodeHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hexChars);
    }

    public synchronized static String salt32Md5(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(toBytes(data));
            return encodeHex(digest.digest());
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException(nsae);
        }
    }

    public static String signature(String jsonStr, String appSecret) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return salt32Md5(jsonStr + "|" + appSecret);
    }
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return new HashMap<Object,Object>();
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static void main(String[] args) {

//        "{requestTime=1568016547262, apiVersion=0.1, data={channel=0, organId=21311124}, requestId=1568016547262, appKey=a48f90ff6c6860f79560dcde5775513d, noncestr=1568016547262}"
        Map map = new HashMap();
        Map map2 = new HashMap();
        map2.put("custId","11944");
        map2.put("cardCode","");
//        map2.put("templateCode","1");

        map.put("requestId","1");
        map.put("requestTime","1568974480634");
        map.put("noncestr","1");
        map.put("appKey","ljYDrr-test");
//        map.put("appKey","a48f90ff6c6860f79560dcde5775513d");
        map.put("apiVersion","0.0.1");
        map.put("data",map2);
        String json = JSON.toJSONString(map);
        System.out.println(json);
//        System.out.println(signature(json,"NEXP5sMhnxCyHmFi2epYgG2iEj7YgpM7"));
        System.out.println(signature(json,"NEXP5sMhnxCyHmFi2epYgG2iEj7YgpM7"));
        System.out.println(System.currentTimeMillis());


        JSONObject obj = JSON.parseObject("{\"apiVersion\":\"0.1\",\"appKey\":\"a48f90ff6c6860f79560dcde5775513d\",\"data\":{\n" +
                "\"templateDifId\": \"571\",\n" +
                "\"variables\": [{\n" +
                "\"attrCode\": \"REMARK1\",\n" +
                "\"attrInstId\": \"12035513\",\n" +
                "\"attrValue\": \"设备故障333\",\n" +
                "\"widgetType\": \"textarea\"\n" +
                "}, {\n" +
                "\"attrCode\": \"SCZP_ID\",\n" +
                "\"attrInstId\": \"12035514\",\n" +
                "\"attrValue\": \"\n" +
                "http://nginx_intranet.midea:8084/uhomecp-warehouse/static/default/component/jstree-3.0.9/dist/themes/default/32px.png\",\n" +
                "\"widgetType\": \"photo\"\n" +
                "}, {\n" +
                "\"attrCode\": \"EQUIMENT\",\n" +
                "\"attrInstId\": \"12035515\",\n" +
                "\"attrValue\": \"设备编码啊\",\n" +
                "\"widgetType\": \"equipment\"\n" +
                "}, {\n" +
                "\"attrCode\": \"XJ_device_name_id\",\n" +
                "\"attrInstId\": \"12035516\",\n" +
                "\"attrValue\": \"设备名称啊\",\n" +
                "\"widgetType\": \"text\"\n" +
                "}, {\n" +
                "\"attrCode\": \"equmentPositon\",\n" +
                "\"attrInstId\": \"12035517\",\n" +
                "\"attrValue\": \"设备位置信息啊\",\n" +
                "\"widgetType\": \"text\"\n" +
                "}],\n" +
                "\"organId\": \"21322160\",\n" +
                "\"busiTypeCode\": \"4001\"\n" +
                "},\"noncestr\":\"1568029359558\",\"requestId\":\"1568029359558\",\"requestTime\":\"1568619352602\"}");

        System.out.println(obj.toJSONString());
    }

}
