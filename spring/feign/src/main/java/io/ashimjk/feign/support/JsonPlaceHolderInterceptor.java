package io.ashimjk.feign.support;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonPlaceHolderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("RequestTemplate : [{}]", requestTemplate);
    }
}
