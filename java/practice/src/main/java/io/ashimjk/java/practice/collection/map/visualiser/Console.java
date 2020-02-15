package io.ashimjk.java.practice.collection.map.visualiser;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class Console {

    private static final String ANSI_COLOUR_RESET = "\u001B[0m";
    private static final String CLEAR = "\033[H\033[2J";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    private final PrintStream out;
    private final InputStream in;

    public Console() {
        this(System.out, System.in);
    }

    public Console(final PrintStream out, final InputStream in) {
        this.out = out;
        this.in = in;
    }

    public void await() {
        try {
            this.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.print(CLEAR);
    }

    public void println(final Object value) {
        this.out.println(value);
    }

    public void print(final Object value) {
        this.out.print(value);
    }

    public void printf(final String format, final Object... args) {
        this.out.printf(format, args);
    }

    public void red() {
        this.print(ANSI_RED);
    }

    public void yellow() {
        this.print(ANSI_YELLOW);
    }

    public void green() {
        this.print(ANSI_GREEN);
    }

    public void resetColour() {
        this.print(ANSI_COLOUR_RESET);
    }

    public void indent(final int index) {
        IntStream.range(0, index).forEach(i -> this.print('\t'));
    }

}
