package com.xiong.day01;

import lombok.SneakyThrows;

import java.util.Date;

public class EndRunable {
    static class RunThread implements Runnable {
        @Override
        public void run() {
            String threadNme = Thread.currentThread().getName();
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(threadNme + "===============>run");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    System.out.println("111111111111111111111111");
                }
            }
            System.out.println(threadNme + "=============>" + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread run = new Thread(new RunThread());
        run.start();
        System.out.println("Main==============>begin interrupt()" + new Date());
        run.interrupt();
        System.out.println("Main==============>end interrupt()" + new Date());
    }
}
