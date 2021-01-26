package com.xiong.day01;

public class ThreadDemo  {
    public static void main(String[] args) {

        
        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("java thread");
            }
        };
        t1.start();
    }
}
