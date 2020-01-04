package io.ashimjk.liquibase.entity.deal;

import io.ashimjk.liquibase.model.deal.DealType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "type_of_dealing")
public class TypeOfDealingEntity implements Serializable {

    private static final long serialVersionUID = 7775774316707767509L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ElementCollection
    @OrderColumn(name = "services_order_id")
    private List<String> services = new ArrayList<>();

}
