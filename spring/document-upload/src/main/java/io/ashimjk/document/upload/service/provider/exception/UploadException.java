package io.ashimjk.document.upload.service.provider.exception;

public class UploadException extends RuntimeException {

    private String key;
    private String reason;

    public UploadException(String key, String reason) {
        this.key = key;
        this.reason = reason;
    }

    public String getKey() {
        return key;
    }

    public String getReason() {
        return reason;
    }

}
