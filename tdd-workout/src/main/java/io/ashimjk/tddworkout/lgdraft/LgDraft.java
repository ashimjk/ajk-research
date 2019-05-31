package io.ashimjk.tddworkout.lgdraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class LgDraft {

    @Id
    private String reference;

    @Column
    private String data;

}
