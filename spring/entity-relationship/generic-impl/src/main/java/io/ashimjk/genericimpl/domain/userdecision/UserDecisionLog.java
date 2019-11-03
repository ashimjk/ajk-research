package io.ashimjk.genericimpl.domain.userdecision;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class UserDecisionLog implements Serializable {

    private static final long serialVersionUID = -53101308098653620L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String userId;
    private String action;
    private String comment;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

}
