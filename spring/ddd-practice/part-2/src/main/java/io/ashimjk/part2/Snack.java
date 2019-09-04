package io.ashimjk.part2;

import lombok.Getter;

@Getter
public class Snack extends AggregateRoot {

    public static Snack NONE = new Snack(0, "None");
    public static Snack CHOCOLATE = new Snack(1, "Chocolate");
    public static Snack SODA = new Snack(2, "Soda");
    public static Snack GUM = new Snack(3, "Gum");

    private String name;

    private Snack(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
