package com.xiong.spring.example;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class EmailService {

    private void sendMail() {
        System.out.println("发送快递");
    }

    @Async
    @EventListener
    public void handleOrderEvent() {
        this.sendMail();
    }
}
