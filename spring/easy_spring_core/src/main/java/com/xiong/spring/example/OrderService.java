package com.xiong.spring.example;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    public void prepareOrder(String promotion) {
        switch (promotion) {
            case "promotion 1":
                // 促销1的算法
                calPromotion1(promotion);
            case "":
                // 促销2的算法
                calPromotion2(promotion);
            default:
                return;
        }
    }

    private String calPromotion1(String promotion) {
        System.out.println("");
        return "";
    }
    private String calPromotion2(String promotion) {
        System.out.println("");
        return "";
    }

    // 方法设计原则 ：单一职责原则
    // 1.某个方法是干什么的,由方法去组装功能
    // 2.某个类都是独立干某件事情的
    // 分析：方法里面变的是什么
    // 促销的金额的算法! 同一行为的不同算法!

    // 同一行为的不同算法实现, 我们可以用接口来定义行为,不同的算法分别去实现接口。
    //    设计原则：对修改关闭，对扩展开放

    // switch 需要知道所有实现类
    public void prepareOrder1(String promotion) {
        switch (promotion) {
            case "promotion 1":
                // 促销1的算法
//                new PromotionCal.calculate(promotion);
            case "":
                // 促销2的算法
//                new PromotionCal.calculate(promotion);
            default:
                return;
        }
    }

    // 面向接口编程
    static class Factory {
        static Map<String, String> calMap;
        static {
            // 初始方案
            calMap = new HashMap<>();
            calMap.put("prom1", "calObject1");
            calMap.put("prom2", "calObject2");
            calMap.put("prom3", "calObject3");
            // 改为从配置文件进行
            // for

        }
        static String get(String promotion) {
            // prom1 --> Promlcal
            return calMap.get(promotion);
        }
        // 达到了我们想要的 一次写好代码 就不改吗

    }
    public void prepareOrder2(String promotion) {
//        Factory.get(promotion).cal();
    }

    @Autowired
    private ApplicationContext context;
    @Autowired
    private Map<String, Object> promotionCalculateions;
    @Autowired
    private List<Object> promList;
    // 使用spring
    public void prepareOrder3(String promotion) {
//          this.context.getBean(promotion).calculate();
    }

}
