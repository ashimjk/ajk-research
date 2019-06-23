package io.ashimjk.merge;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
@Setter
public abstract class WorkflowEntity<T> implements Serializable {

    private String id;
    private LocalDateTime creationDate;
    private String reference;
    private String currentTask;
    private String currentProcess;

    private String processInstanceId;
    private String issuingBankReference;
    private String advisingBankReference;

    protected static <T extends WorkflowEntity> boolean hasInCompleteValue(WorkflowEntity<T> dto) {
        return !Objects.nonNull(dto) || dto.hasInCompleteValue();
    }

    protected static <S> S getIfNotNull(S newVal, S oldVal) {
        return isNull(newVal) ? oldVal : newVal;
    }

    protected static <S extends WorkflowEntity> S copyWithUpdateValue(S old, S newVal) {
        if (newVal == null) {
            return old;
        }
        if (old == null) {
            return newVal;
        }
        return (S) newVal.copyWithUpdateValue(old);
    }

    protected boolean hasInCompleteValue() {
        return false;
    }

    protected T copyWithUpdateValue(T dto) {
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLcReference() {
        return reference;
    }

    public boolean isProcessCompleted() {
        return isNull(currentProcess) && isNull(currentTask);
    }

}
