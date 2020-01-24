package io.ashimjk.stocktrading;

public interface LogExecutionTime {

    static void start(Runnable runnable) {
        long start = System.currentTimeMillis();

        runnable.run();

        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println(String.format(" Done in %ss", elapsed));
    }

}
