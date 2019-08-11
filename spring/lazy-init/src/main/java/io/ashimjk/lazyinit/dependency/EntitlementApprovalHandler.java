package io.ashimjk.lazyinit.dependency;

import org.springframework.beans.factory.annotation.Autowired;

public class EntitlementApprovalHandler {

    @Autowired
    private RestTemplate restTemplate;

    public void print() {
        System.out.println(restTemplate);
    }

    @Override
    public String toString() {
        return "EntitlementApprovalHandler";
    }
}
