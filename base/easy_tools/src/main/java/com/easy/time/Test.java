package com.easy.time;

public class Test {
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
    public static void main(String[] args) throws InterruptedException {
//        long startNano = System.nanoTime();
//        long start = System.currentTimeMillis();
//        Thread.sleep(20000);
//        long endNano = System.nanoTime();
//        long end = System.currentTimeMillis();
//        System.out.println("nano=========>" + (endNano - startNano));
//        System.out.println("millis=========>" + (end - start));
        //20009785200
        //20010
        lock(3000,3);
    }

    public static boolean lock(long timeout, int expire) {
        long nano = System.nanoTime();
        long start = System.currentTimeMillis();
        timeout *= MILLI_NANO_CONVERSION;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                System.out.println("222==================>"+((System.nanoTime() - nano)));
                // 短暂休眠，避免出现活锁
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Locking error", e);
        }
        System.out.println("111==================>"+((System.nanoTime() - nano)));
        System.out.println("123==================>"+((System.currentTimeMillis() - start)));
        return false;
    }
}
