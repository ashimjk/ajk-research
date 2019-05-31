package io.ashimjk.annotation.processor.app;

import io.ashimjk.annotation.processor.factory.AutoElement;

@AutoElement(tag = AnimalTags.CAT)
public class Cat implements Animal {

    @Override
    public void bark() {
        System.out.println("meow");
    }

}