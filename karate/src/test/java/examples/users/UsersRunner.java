package examples.users;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersRunner {

//    @Karate.Test
//    Karate testUsers() {
//        return new Karate().feature("users").relativeTo(getClass());

    @Test
    void testParallel() {
        Results results = Runner.parallel(getClass(), 5, "target/surefire-reports");
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
