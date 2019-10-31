package io.ashimjk.jacoco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JacocoApplicationTests {

    @Autowired
    private ApplicationContext ac;

    @Test
    void contextLoads() {
        Calculator calculator = ac.getBean(Calculator.class);
        assertTrue(calculator instanceof CalculatorImpl);

        CalculatorController calcController = ac.getBean(CalculatorController.class);
        assertNotNull(calcController);
    }
}
