package io.ashimjk.annotation.processor.ex.app;

public class MainActivity {

    public static void main(String[] args) {

        oldWay();
        usingAnnotationProcessor();

    }

    private static void oldWay() {
        Animal cat = OldAnimalFactory.createAnimal(AnimalTags.CAT);
        cat.bark();
    }

    private static void usingAnnotationProcessor() {
        Animal dog = AnimalFactory.createAnimal(AnimalTags.DOG);
        dog.bark();
    }
}