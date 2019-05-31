package io.ashimjk.annotation.processor.ex.app;

import io.ashimjk.annotation.processor.ex.factory.AutoElement;

@AutoElement(tag = AnimalTags.CAT)
public class Cat implements Animal {

    @Override
    public void bark() {
        System.out.println("meow");
    }

}