package io.ashimjk.lazyinit.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Usecase {

    @Lazy
    @Autowired(required = false)
    private EntitlementApprovalHandler entitlementApprovalHandler;

    public void print() {
        System.out.println(entitlementApprovalHandler);
    }

}
