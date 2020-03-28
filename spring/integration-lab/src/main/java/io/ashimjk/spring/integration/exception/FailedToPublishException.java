package io.ashimjk.spring.integration.exception;

public class FailedToPublishException extends RuntimeException {

    private static final long serialVersionUID = 4233708253508708022L;

    public FailedToPublishException(String userId) {
        super("Failed to publish stream for : " + userId);
    }

}
