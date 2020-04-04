package io.ashimjk.reactor;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.ashimjk.reactor.Utils.isSlowTime;
import static io.ashimjk.reactor.Utils.sleep;

class UtilsTest {

    @Test
    void test() {
        while (true) {
            System.out.println(String.format(new Date() + " is %sslow time ", isSlowTime() ? "" : "NOT "));
            sleep(1_00);
        }
    }

}
