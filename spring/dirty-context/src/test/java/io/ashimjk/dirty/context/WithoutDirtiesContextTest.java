package io.ashimjk.dirty.context;

import io.ashimjk.dirty.context.without.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class WithoutDirtiesContextTest {

    @Autowired
    protected Employee emp;

    @Before
    public void beforeTest() {
        System.out.println("beforeTest: " + emp);
        emp.setName("Roy");
    }

    @Test
    public void emp1Test() {
        System.out.println("emp1Test: " + emp);
    }

    @Test
    public void emp2Test() {
        System.out.println("emp2Test " + emp);
        emp.setName("Sam");
    }

    @Test
    public void emp3Test() {
        System.out.println("emp3Test " + emp);
        emp.setName("Kim");
    }

    @Test
    public void emp4Test() {
        System.out.println("emp4Test " + emp);
        emp.setName("Rita");
    }

    @After
    public void afterTest() {
        System.out.println("afterTest: " + emp);
    }

    @Configuration
    @ComponentScan("io.ashimjk.dirty.context")
    static class Config {

    }

}
