package io.ashimjk.reactor;

import java.util.Date;

public class Utils {
    private static long start = System.currentTimeMillis();

    public static void print(Object s) {
        System.out.printf("%s:%s%n", new Date(), s);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Exiting");
        }
    }

    public static Boolean isSlowTime() {
        return (System.currentTimeMillis() - start) % 12_000 >= 3_000;
    }

    public static Boolean isFastTime() {
        return !isSlowTime();
    }

}
