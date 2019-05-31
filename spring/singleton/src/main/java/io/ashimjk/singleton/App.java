package io.ashimjk.singleton;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void test1() {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        Singleton singleton = ctx.getBean("singleton", Singleton.class);

        print(singleton, "singleton before: ", "prototype before: ");

        singleton.setText("Modified");
        singleton.getPrototype().setText("Modified");

        // 1st retrieval
        singleton = ctx.getBean("singleton", Singleton.class);
        print(singleton, "singleton after: ", "prototype after: ");

        // 2st retrieval
        singleton = ctx.getBean("singleton", Singleton.class);
        print(singleton, "singleton after: ", "prototype after: ");

        ctx.close();

    }

    private static void print(Singleton singleton, String s, String s2) {
        System.out.println();
        System.out.println(s + singleton.getText());
        System.out.println(s2 + singleton.getPrototype().getText());
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        System.out.println("----------------Singleton-------------------");

        System.out.println("----------------1st Retrival-------------------");
        Singleton singleton = ctx.getBean("singleton", Singleton.class);
        System.out.println(singleton.getText());

        singleton.setText("Modified1");

        singleton = ctx.getBean("singleton", Singleton.class);
        System.out.println("----------------2st Retrival-------------------");
        System.out.println(singleton.getText());

        singleton.setText("Modified2");

        singleton = ctx.getBean("singleton", Singleton.class);
        System.out.println("----------------3st Retrival-------------------");
        System.out.println(singleton.getText());

        System.out.println("----------------Prototype-------------------");

        System.out.println("----------------1st Retrival-------------------");
        Prototype prototype = ctx.getBean("prototype", Prototype.class);
        System.out.println(prototype.getText());

        prototype.setText("Modified1");

        prototype = ctx.getBean("prototype", Prototype.class);
        System.out.println("----------------2st Retrival-------------------");
        System.out.println(prototype.getText());

        prototype.setText("Modified2");

        prototype = ctx.getBean("prototype", Prototype.class);
        System.out.println("----------------3st Retrival-------------------");
        System.out.println(prototype.getText());

        ctx.close();

    }

}
