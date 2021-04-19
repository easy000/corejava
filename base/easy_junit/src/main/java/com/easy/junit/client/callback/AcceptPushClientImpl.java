package com.easy.junit.client.callback;

import Ice.Current;
import segi.common.mq.push.client.Client.MqPushResponse;
import segi.common.mq.push.client.Client._MQAcceptClientDisp;

/**
 * 接受服务端推送数据的客户端实现类
 * @author tanjun
 *
 */
public class AcceptPushClientImpl extends _MQAcceptClientDisp {

	/**
	 * 接受服务端推送的数据
	 * 如果code返回非0那么久代表客户端不签收数据,mq消费者不会提交offset,下次会重新推送
	 */
	@Override
	public MqPushResponse accept(MqPushResponse response, Current __current) {
		MqPushResponse clientResponse = new MqPushResponse();
		try{
			if(response.getCode().equals("0")){
				System.out.println(response.getData());
				clientResponse.setCode("1");
				clientResponse.setMessage("测试阶段，不想收");
			}else{
				System.out.println("推送数据出错:" + response.getMessage());
				clientResponse.setCode("1");
			}
		}catch (Throwable e){//自己的业务异常一定要自己处理,别抛到服务端,看异常情况来定是否返回code0 或 code1
			e.printStackTrace();
			clientResponse.setCode("1");
		}
		return clientResponse;
	}
}
