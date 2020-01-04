package io.ashimjk.liquibase.model.userdecision;

import org.springframework.util.StringUtils;

public enum ApprovalStatus {
    RECOMMEND_APPROVAL, RECOMMEND_REJECTION, APPROVED, REJECTED, NONE;

    public static ApprovalStatus of(String approvalStatus) {
        if (StringUtils.isEmpty(approvalStatus)) {
            return NONE;
        } else {
            return ApprovalStatus.valueOf(approvalStatus);
        }
    }
}
