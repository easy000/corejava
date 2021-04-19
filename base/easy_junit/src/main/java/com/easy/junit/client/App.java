package com.easy.junit.client;

import com.easy.junit.client.callback.AcceptPushClientImpl;
import com.glacier.GlacierClient;
import segi.common.mq.push.client.Client.MqPushResponse;
import segi.common.mq.push.client.Client._MQAcceptClientDisp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 客户端通知服务端,启动推送数据服务
 * 起多个客户端 不要起太快了
 * @author tanjun
 *
 */
public class App implements Runnable{
	static ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
	public static void main(String[] args) {
        es.schedule(new App(), 1, TimeUnit.SECONDS);
        //es.schedule(new App(), 5, TimeUnit.SECONDS);
        //es.schedule(new App(), 10, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		String iceConfigPath = "D:\\Segi\\knowledge_git\\corejava\\base\\easy_junit\\src\\main\\resources\\iceglacier2_beta.properties";
		_MQAcceptClientDisp impl = new AcceptPushClientImpl();
		String tags = "*";
		String basecode = "JIESHUNTEST";//四格互联分给各厂商到basecode
		try{
			final GlacierClient gc = new GlacierClient(iceConfigPath,impl,tags,basecode);
			Runnable r = new Runnable() {
				@Override
				public void run() {
					try {
						MqPushResponse res = gc.getResponse();
						if(res != null) {
							//只会打印现在读取的时候的状态,如果发生了断开重连,也许能打印出来,如果想记录的更清楚,把下面的任务执行的频率调高点
							System.out.println("客户端运行状态 code = " + res.getCode() + ",message = " + res.getMessage());
						}else {
							System.out.println("客户端还未启动");
						}
					}catch (Exception e) {//加异常捕获,是因为线程执行中出现异常,则会丢弃这个任务
						e.printStackTrace();
					}
				}
			};
			es.scheduleAtFixedRate(r,1 ,1, TimeUnit.SECONDS);
		}catch (Throwable e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}