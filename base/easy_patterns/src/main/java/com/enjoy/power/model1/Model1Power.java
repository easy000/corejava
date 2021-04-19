package com.enjoy.power.model1;

import com.enjoy.power.AbstractPowerClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "appWxPayClient")
public class Model1Power extends AbstractPowerClient {

    @Override
    public Object doQueryInvoke(Object obj) {
        // 模式1 业务处理
        return null;
    }

    @Override
    public Object doUpdateInvoke(Map<java.lang.String, java.lang.String> addInfo) {
        // 模式1 业务处理
        return null;
    }
}
