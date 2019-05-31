package io.ashimjk.annotation.processor.app;

import io.ashimjk.annotation.processor.factory.AutoElement;

@AutoElement(tag = AnimalTags.DOG)
public class Dog implements Animal {

    @Override
    public void bark() {
        System.out.println("woo");
    }

}