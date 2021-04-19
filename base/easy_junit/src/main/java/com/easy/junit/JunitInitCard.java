package com.easy.junit;

import IceExt.IceClientUtil;
import com.alibaba.fastjson.JSONObject;
import com.easy.junit.client.HttpClient;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import segi.common.power.service.Service.GetPowerInfo;
import segi.common.power.service.Service.GetPowerInfoPrx;

@ContextConfiguration(locations = { "classpath*:spring/spring-common.xml" })
@RunWith(JUnit4ClassRunner.class)
public class JunitInitCard {

	public void testIdentityInfo() {
		GetPowerInfo prx = (GetPowerInfo) IceClientUtil.getServicePrxByClass(GetPowerInfoPrx.class);
		prx.getBaseInfo("");
	}

	public void testQueryOrgan() {
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
			String msg = HttpClient.exe(miyao,param.toJSONString(), HttpClient.httpPath.get("154"), "getCarParingInfoByOrganId"); //getBaseInfomation
			System.out.println(msg);
		}catch(Throwable e){
			e.printStackTrace();
			return;
		}
	}
}
