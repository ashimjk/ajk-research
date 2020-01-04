package io.ashimjk.liquibase.entity.shared;

import io.ashimjk.liquibase.model.shared.Workflow;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
public class WorkflowEntity implements Serializable {

    private static final long serialVersionUID = 2358652104612155325L;

    private String processInstanceId;
    private String currentProcess;
    private String currentTask;

    @Enumerated(EnumType.STRING)
    private Workflow.Status status;

    public WorkflowEntity() {
        this.status = Workflow.Status.CREATED;
    }

}
