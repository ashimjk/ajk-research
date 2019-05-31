package io.ashimjk.annotation.processor.ex.app;

public final class OldAnimalFactory {

    public static Animal createAnimal(String type) {

        switch (type) {
            case "dog":
                return new Dog();
            case "cat":
                return new Cat();
        }

        throw new RuntimeException("not support type");
    }

}
