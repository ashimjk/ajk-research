package io.ashimjk.spring.tx.propagation;

import io.ashimjk.spring.tx.db.User;
import io.ashimjk.spring.tx.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ashimjk on 2/9/2019
 */
@Service
public class OuterBean {

    private final UserDao userDAO;
    private final InnerBean innerBean;

    @Autowired
    public OuterBean(UserDao userDAO, InnerBean innerBean) {
        this.userDAO = userDAO;
        this.innerBean = innerBean;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired(User user) {
        this.userDAO.insertUser(user);

        try {

            this.innerBean.testRequired();

        } catch (RuntimeException e) {
            System.out.println("testRequired : error occurred");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiresNew(User user) {
        this.userDAO.insertUser(user);

        try {

            this.innerBean.testRequiresNew();

        } catch (Exception e) {
            System.out.println("testRequiresNew : error occurred");
        }
    }

}
