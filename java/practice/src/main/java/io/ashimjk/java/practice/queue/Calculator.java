package io.ashimjk.java.practice.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class Calculator {

    // 1 - 3 + 2 + 4
    public int evaluate(final String input) {
        Deque<String> stack = new ArrayDeque<>(Arrays.asList(input.split(" ")));

        while (stack.size() > 1) {
            final int left = parseInt(stack.pop());
            final String operator = stack.pop();
            final int right = parseInt(stack.pop());

            int result = 0;
            switch (operator) {
                case "+":
                    result = left + right;
                    break;
                case "-":
                    result = left - right;
                    break;
                default:
            }

            stack.push(String.valueOf(result));
        }

        return parseInt(stack.pop());
    }

}
