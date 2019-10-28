package io.ashimjk.genericimpl.domain.userdecision;

import io.ashimjk.genericimpl.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDecisionLog extends BaseEntity {

    private static final long serialVersionUID = 130615889992020097L;

    private String userId;
    private String tenantId;
    private String comment;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

}
