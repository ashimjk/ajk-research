package io.ashimjk.transaction;

import io.ashimjk.transaction.db.User;
import io.ashimjk.transaction.propagation.OuterBean;
import io.ashimjk.transaction.sample.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ashimjk on 2/9/2019
 */
@Component
public class TransactionRunner implements CommandLineRunner {

    private final ApplicationContext context;
    private final UserService userService;
    private final OuterBean outerBean;

    @Autowired
    public TransactionRunner(ApplicationContext context, UserService userService, OuterBean outerBean) {
        this.context = context;
        this.userService = userService;
        this.outerBean = outerBean;
    }

    @Override
    public void run(String... args) {

        //		this.localTest();
        this.propagationTest();

        System.out.println(this.userService.getUsers());

        ((ConfigurableApplicationContext) context).close();
    }

    private void propagationTest() {
        User user = User.builder().id(1).name("ram").address("boudha").build();

        try {
            this.outerBean.testRequired(user);
        } catch (Exception e) {
            // catch exception raised from transaction rollback
            System.out.println("propagationTest : error occurred");
        }

        this.outerBean.testRequiresNew(user);
    }

    private void localTest() {
        try {
            this.userService.insert();
        } catch (Exception e) {
            System.out.println("localTest : error occurred");
        }
    }

}
