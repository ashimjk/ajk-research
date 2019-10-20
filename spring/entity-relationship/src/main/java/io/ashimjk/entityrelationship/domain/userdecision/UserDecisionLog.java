package io.ashimjk.entityrelationship.domain.userdecision;

import io.ashimjk.entityrelationship.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDecisionLog extends BaseEntity {

    private static final long serialVersionUID = 130615889992020097L;

    private String comment;
    private String userId;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private String tenantId;


}
