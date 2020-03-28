package io.ashimjk.spring.integration.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 3669087246991047196L;

    @Id
    private String id;
    private String name;
    private String address;
    private LocalDateTime timestamp;

}
