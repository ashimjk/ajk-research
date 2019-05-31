package io.ashimjk.java.practice.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalFeatures {

    public static void main(String[] args) {

        // 1. Scaling and Rounding modes
        BigDecimal bigDecimal = new BigDecimal(23.12);
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println();

        // In most of the cases conventional logic is to have a scale of 2
        // (2 digits after the decimal) and rounding up if the digit after the scale is >=5

        BigDecimal bigDecimal2 = new BigDecimal("23.1256");
        System.out.println("bigDecimal2 : " + bigDecimal2.setScale(2, RoundingMode.HALF_UP));
        System.out.println();

        BigDecimal bigDecimal3 = new BigDecimal("23.1236");
        System.out.println("bigDecimal3 : " + bigDecimal3.setScale(2, RoundingMode.HALF_UP));
        System.out.println();

        BigDecimal bigDecimal4 = new BigDecimal("-15.567");
        System.out.println("bigDecimal4 : " + bigDecimal4.setScale(2, RoundingMode.HALF_UP));
        System.out.println();

        // 2. No Overloaded operators
        BigDecimal bigDecimal5 = new BigDecimal("15.567");
        BigDecimal result = BigDecimal.valueOf(68).multiply(bigDecimal5);
        System.out.println("result = " + result);
        System.out.println();

        // 3. Use compareTo() to compare BigDecimals not equals()
        BigDecimal bd1 = new BigDecimal("2.00");
        BigDecimal bd2 = new BigDecimal("2.0");
        System.out.println("bd1 equals bd2 - " + bd1.equals(bd2));
        System.out.println();

        BigDecimal bd3 = new BigDecimal("2.00");
        BigDecimal bd4 = new BigDecimal("2.0");
        System.out.println("bd3 equals bd4 - " + bd3.compareTo(bd4));
        System.out.println();

        // 4. BigDecimals are immutable
        // Any change to BigDecimal value, result in new value which is always return
        BigDecimal bd5 = new BigDecimal("2.00");
        BigDecimal bd6 = bd5.add(BigDecimal.valueOf(2.00));
        System.out.println("bd5 = " + bd5);
        System.out.println("bd6 = " + bd6);
    }

}
