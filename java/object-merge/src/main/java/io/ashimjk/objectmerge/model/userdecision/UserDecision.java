package io.ashimjk.objectmerge.model.userdecision;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDecision implements Serializable {

    private static final long serialVersionUID = -5971119962240355952L;

    private Long id;
    private String userId;
    private String beneficiaryRef;
    private ApprovalStatus approvalStatus;
    private String comments;
    private String action;
    private String process;
    private String processInstanceId;
    private LocalDateTime creationDate;

}
