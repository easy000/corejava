package com.enjoy.power;

import java.util.Map;

public interface PowerClient {

    Object queryPower(Object request);

    Object updatePower(Map<String, String> paramsMap) ;
}
