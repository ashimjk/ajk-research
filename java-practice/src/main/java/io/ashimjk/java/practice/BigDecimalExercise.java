package io.ashimjk.java.practice;

import java.math.BigDecimal;

public class BigDecimalExercise {

    public static void main(String[] args) {

        precisionProblemWithDouble();

        precisionProblemWithFloat();

        precisionSolutionWithBigDecimalUsingStringParameter();

        precisionProblemWithBigDecimalUsingDoubleParameter();

        precisionProblemWithBigDecimalUsingFloatParameter();
    }

    private static void precisionProblemWithBigDecimalUsingFloatParameter() {
        System.out.println("----Financial calculation with BigDecimal using float parameter-------");
        BigDecimal b1 = new BigDecimal(374.56f);

        System.out.println(b1);

        System.out.println();
    }

    private static void precisionProblemWithBigDecimalUsingDoubleParameter() {
        System.out.println("----Financial calculation with BigDecimal using double parameter-------");
        BigDecimal b1 = new BigDecimal(374.56);

        System.out.println(b1);

        System.out.println();
    }

    private static void precisionSolutionWithBigDecimalUsingStringParameter() {
        System.out.println("----Financial calculation with BigDecimal using String Parameter-------");
        BigDecimal b1 = new BigDecimal("374.56");
        BigDecimal b2 = new BigDecimal("374.26");

        System.out.println("(b1 - b2) = " + (b1.subtract(b2)));

        System.out.println();
    }

    private static void precisionProblemWithFloat() {
        System.out.println("----Financial calculation with float-------");
        float f1 = 374.56f;
        float f2 = 374.26f;

        System.out.println("(f1 - f2) = " + (f1 - f2));

        System.out.println();
    }

    private static void precisionProblemWithDouble() {
        System.out.println("----Financial calculation with double-------");
        double d1 = 374.56;
        double d2 = 374.26;

        System.out.println("(d1 - d2) = " + (d1 - d2));

        System.out.println();
    }

}
