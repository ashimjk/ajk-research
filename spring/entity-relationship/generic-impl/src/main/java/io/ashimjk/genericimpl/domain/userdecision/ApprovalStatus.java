package io.ashimjk.genericimpl.domain.userdecision;

public enum ApprovalStatus {
    RECOMMEND_APPROVAL, RECOMMEND_REJECTION, APPROVED, REJECTED;

    public static ApprovalStatus of(String status) {
        return ApprovalStatus.valueOf(status);
    }
}
