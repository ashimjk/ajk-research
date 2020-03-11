package io.ashimjk.objectmerge.model.userdecision;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ApprovalStatus {
    RECOMMEND_APPROVAL, RECOMMEND_REJECTION, APPROVED, REJECTED;

    public static ApprovalStatus of(String approvalStatus) {
        boolean isApprovalStatusAvailable = Arrays.stream(ApprovalStatus.values())
                .map(ApprovalStatus::name)
                .collect(Collectors.toList())
                .contains(approvalStatus);

        return isApprovalStatusAvailable ? valueOf(approvalStatus) : null;
    }
}
