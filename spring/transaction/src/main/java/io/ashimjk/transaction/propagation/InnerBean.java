package io.ashimjk.transaction.propagation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ashimjk on 2/9/2019
 */
@Service
public class InnerBean {

    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        throw new RuntimeException("Rollback this transaction!");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testRequiresNew() {
        throw new RuntimeException("Rollback this transaction!");
    }

}
