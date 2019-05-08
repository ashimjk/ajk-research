package io.ashimjk.annotation.processor.ex.app;

import io.ashimjk.annotation.processor.ex.factory.AutoElement;

@AutoElement(tag = AnimalTags.DOG)
public class Dog implements Animal {
    @Override
    public void bark() {
        System.out.println("woo");
    }
}