package com.enjoy.power;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class AbstractPowerClient implements PowerClient {

    private static Logger logger = LoggerFactory.getLogger(AbstractPowerClient.class);

    @Override
    public Object queryPower(Object request)  {
        // 业务数据组装
        try {
            Object body = doQueryInvoke(request);
        } catch (Exception e) {
        }
        // 查询出来的数据进行组装
        return null;
    }

    @Override
    public Object updatePower(Map<String, String> paramsMap)  {
        // 业务数据组装
        try {
            Object body = doUpdateInvoke(paramsMap);
        } catch (Exception e) {
        }
        // 查询出来的数据进行组装
        return null;
    }

    public abstract Object doQueryInvoke(Object obj);

    public abstract Object doUpdateInvoke(Map<String, String> addInfo);

}
