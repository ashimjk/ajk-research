package io.ashimjk.java.practice.collection.map.visualiser;

import java.lang.reflect.Field;
import java.util.TreeMap;

public class TreeMapVisualiser {

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    private static final Field rootField;
    private static final Field colorField;
    private static final Field leftField;
    private static final Field rightField;

    static {
        Type entry = new Type("java.util.TreeMap$Entry");
        Type treeMap = new Type(TreeMap.class);

        rootField = treeMap.getField("root");
        leftField = entry.getField("left");
        rightField = entry.getField("right");
        colorField = entry.getField("color");
    }

    private final Console console;

    public TreeMapVisualiser(final Console console) {
        this.console = console;
    }

    public void visualise(final TreeMap<?, ?> map) {
        this.console.printf("Size: %d, %n", map.size());

        Object root = Type.get(rootField, map);

        this.visualiseSubTree(root, 0);
    }

    private int visualiseSubTree(final Object node, int index) {
        this.console.indent(index);
        final boolean colour = (boolean) Type.get(colorField, node);
        if (colour == RED) {
            this.console.red();
        }

        this.console.println(node);
        this.console.resetColour();

        return 1
                + this.visualiseBranch(node, index, leftField)
                + this.visualiseBranch(node, index, rightField);
    }

    private int visualiseBranch(final Object node, final int index, final Field field) {
        Object branch = Type.get(field, node);
        if (branch != null) {
            return this.visualiseSubTree(branch, index + 1);
        }
        return 0;
    }

}
