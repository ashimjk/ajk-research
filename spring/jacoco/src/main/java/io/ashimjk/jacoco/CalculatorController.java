package io.ashimjk.jacoco;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final Calculator calculator;

    @GetMapping("/")
    public String result(@RequestParam String expression) {
        return Double.toString(calculator.process(expression));
    }

}
