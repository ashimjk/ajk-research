package io.ashimjk.feign.support;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.ashimjk.feign.exception.BadRequestException;
import io.ashimjk.feign.exception.NotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException();
            case 404:
                return new NotFoundException();
            default:
                return new Exception("Generic error");
        }
    }
}
