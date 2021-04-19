package com.enjoy.power.rpc;

import com.enjoy.power.DefaultPowerClient;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class PowerRpc {

    @Resource
    DefaultPowerClient client;

    Object queryPower(Object obj) {
        // 统一数据处理
        try {
            client.queryPower(obj);
        } catch (Exception e) {
            // 异常处理
        }
        return null;
    }

    Object updatePower(Object obj) {
        // 统一数据处理
        Map<String, String> paramsMap = new HashMap<>();
        try {
            client.updatePower(paramsMap);
        } catch (Exception e) {
            // 异常处理
        }
        return null;
    }
}
