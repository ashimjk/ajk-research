package io.ashimjk.jsondiff;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
class UserDecisionResource {

    private String approvalStatus;
    private String comment;
    private String date;
    private String userId;

}
