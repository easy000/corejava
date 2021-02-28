package com.xiangxue.xiong.ch1;

public class EndRunnable {

    static class RunThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("====================>" + Thread.currentThread().isInterrupted());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RunThread());
        Thread.sleep(1000);
        thread.start();
        Thread.sleep(1000);
    }
}
