package io.ashimjk.liquibase.model.userdecision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class UserDecisionLog implements Serializable {

    private static final long serialVersionUID = -5971119962240355952L;

    private String userId;
    private String action;
    private String comment;
    private LocalDateTime date;
    private ApprovalStatus approvalStatus;

}
