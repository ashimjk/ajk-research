package io.ashimjk.liquibase.entity.userdecision;

import io.ashimjk.liquibase.model.userdecision.ApprovalStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "user_decision_log")
public class UserDecisionLogEntity implements Serializable {

    private static final long serialVersionUID = -526480197457470977L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String userId;
    private String action;
    private String comment;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

}
