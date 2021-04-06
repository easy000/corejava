package com.xiong.spring.example.zhuru;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cxx
 * 模拟spring 加载xml配置文件(dom4j)
 * @Date: 2018/6/1 16:38
 */
public class SpringBeanFactory {
    //初始化的bean全用map集合保存
    private static Map<String, Object> beanMap = new HashMap<>();

    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:bean.xml");
        DbSourceConfig db = (DbSourceConfig) factory.getBean("people");
        List<String> list = db.getPartTableList();
        for (String str : list) {
            System.out.println(str);
        }
    }
}