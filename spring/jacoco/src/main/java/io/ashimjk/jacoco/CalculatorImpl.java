package io.ashimjk.jacoco;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component
public class CalculatorImpl implements Calculator {

    @Override
    public double process(String expression) {
        String[] tokens = expression.split(" ");
        Deque<String> operators = new ArrayDeque<>();
        Deque<Double> numbers = new ArrayDeque<>();
        try {
            for (String token : tokens) {
                switch (token) {
                    case "+":
                    case "-":
                    case "/":
                    case "*":
                        while (shouldEvaluate(token, operators.peekFirst())) {
                            evaluate(operators, numbers);
                        }
                        operators.push(token);
                        break;
                    case "(":
                        operators.push(token);
                        break;
                    case ")":
                        for (String op = operators.peekFirst(); !op.equals("("); op = operators.peekFirst()) {
                            evaluate(operators, numbers);
                        }
                        operators.pop();
                        break;
                    default:
                        double d = Double.parseDouble(token);
                        numbers.push(d);
                        break;
                }
            }
            for (String op = operators.peekFirst(); op != null; op = operators.peekFirst()) {
                evaluate(operators, numbers);
            }
        } catch (Exception e) {
            throw new CalculatorException("Invalid expression: " + expression, e);
        }
        double result = numbers.pop();
        if (numbers.size() > 0) {
            throw new CalculatorException("Invalid expression: " + expression);
        }
        return result;
    }

    private void evaluate(Deque<String> operators, Deque<Double> numbers) {
        String op = operators.pop();
        double second = numbers.pop();
        double first = numbers.pop();
        double result = getResult(op, second, first);
        numbers.push(result);
    }

    private double getResult(String op, double second, double first) {
        double result;
        switch (op) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                throw new CalculatorException("Unexpected operator " + op);
        }
        return result;
    }

    private boolean shouldEvaluate(String newOp, String topOp) {
        if (topOp == null || topOp.equals("(")) {
            return false;
        }
        // with 4 standard operators, the only time you don't evaluate is
        // when the new operator is a * or / and the top operator is a + or -
        // topOp newOp shouldEvaluate
        // - - - - - - - - - - - - - 
        // +, - +, - true
        // *, / +, - true
        // +, - *, / false
        // *, / *, / true
        if ((topOp.equals("+") || topOp.equals("-")) && (newOp.equals("*") || newOp.equals("/"))) {
            return false;
        }
        return true;
    }
}