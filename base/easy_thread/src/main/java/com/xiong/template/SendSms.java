package com.xiong.template;

public class SendSms extends SendCustom {
    @Override
    public void to() {
        System.out.println("to");
    }

    @Override
    public void from() {
        System.out.println("from");
    }

    @Override
    public void content() {
        System.out.println("content");
    }

    @Override
    public void send() {
        System.out.println("send");
    }
}
