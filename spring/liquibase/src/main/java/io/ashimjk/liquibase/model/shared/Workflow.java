package io.ashimjk.liquibase.model.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Workflow implements Serializable {

    private static final long serialVersionUID = 8457849660660241303L;

    private String processInstanceId;
    private String currentProcess;
    private String currentTask;
    private Status status;

    public enum Status {
        CREATED, ACTIVE, DELETED, REJECTED, ONTHEFLY, CANCELLED, SUSPENDED;

        public static Status value(String status) {
            return status == null ? null : valueOf(status);
        }
    }

}
