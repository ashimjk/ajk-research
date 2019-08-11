package io.ashimjk.lazyinit;

import io.ashimjk.lazyinit.dependency.Usecase;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyInitApplicationTests {

    @Test
    public void testLazyInitialization() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);

        ctx.refresh();

        Usecase bean = ctx.getBean(Usecase.class);

        bean.print();
    }

}
