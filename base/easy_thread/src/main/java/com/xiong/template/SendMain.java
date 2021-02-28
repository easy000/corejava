package com.xiong.template;

import lombok.Synchronized;

import java.util.concurrent.locks.ReentrantLock;

public class SendMain {
    public static void main(String[] args) {
        SendCustom sc = new SendSms();
        sc.sendinfo();

    }
}
