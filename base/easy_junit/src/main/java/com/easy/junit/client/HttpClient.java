package com.easy.junit.client;

import java.io.IOException;
import java.util.HashMap;

import com.easy.junit.client.SignatureUtils;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


/**
 * netty http 客户端
 * @author tanjun
 *
 */
public class HttpClient {
	public static HashMap<String,String> httpPath = new HashMap<String, String>();
	static{
		httpPath.put("154", "http://beta.uhomecp.com/maindata-api-netty/");
	}
	/**
	 * 发送http请求
	 * @param param 参数json字符串
	 * @param server_context 连接上下文
	 * @param interName 访问的接口名称
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws HttpException 
	 */
	public static String exe(String miyao,String param,String server_context,String interName) throws ClientProtocolException, IOException, HttpException{
		HttpPost httpRequst  = new HttpPost(server_context + "/" + interName);
		//解决中文乱码问题
        StringEntity entity = new StringEntity(param, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpRequst.setEntity(entity);
        httpRequst.setHeader(new BasicHeader("signature", SignatureUtils.signature(param,miyao)));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =  httpclient.execute(httpRequst);
        String msg = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        if(httpResponse.getStatusLine().getStatusCode() == 200){  
        	return msg;
        }else{//请求异常
        	throw new HttpException("错误信息:" + msg);
        }
	}
	
	public static void main(String[] args){
		for (int i = 0 ; i < 1 ; i++) {
		JSONObject data = new JSONObject();
        data.put("baseCode", "JIESHUNTEST");
        data.put("queryOrganId","67");
        JSONObject param = new JSONObject();
        param.put("data",data);
        param.put("appKey","903caa9d0fc60b88bb5dfd259e7a1a6f");
        param.put("requestTime",System.currentTimeMillis());
        param.put("requestId",System.currentTimeMillis());
        param.put("noncestr",System.currentTimeMillis());
        param.put("apiVersion","0.1");
        String miyao = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqW2mif6JIvqwheWpLWHz2V+kyUOII31DWpjhVSNbC7W8qJKpJY/KSjZkkuHnkfMwb3WyWta/RuURvnKtcDPz/zCkky6ez/LVEkqEIjV/h2xla2BWh2AjQh3akeRknvLUZ5aM0EiytmqTATzU0XyB0UjN2YGIyI4lFPtkCEPh6gwIDAQAB";
		try{
			  String msg = exe(miyao,param.toJSONString(), httpPath.get("154"), "getCarParingInfoByOrganId"); //getBaseInfomation
			  System.out.println(msg);
		}catch(Throwable e){
			e.printStackTrace();
			return;
		}
      }
	} 
}
