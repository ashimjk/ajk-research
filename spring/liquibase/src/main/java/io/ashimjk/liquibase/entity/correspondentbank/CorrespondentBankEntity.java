package io.ashimjk.liquibase.entity.correspondentbank;

import io.ashimjk.liquibase.entity.shared.AddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "correspondent_bank")
public class CorrespondentBankEntity implements Serializable {

    private static final long serialVersionUID = -7867870521996314154L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String swiftCode;

    @Embedded
    private AddressEntity address;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    @ElementCollection
    @OrderColumn(name = "account_order_id")
    private List<AccountEntity> accounts = new ArrayList<>();

}
