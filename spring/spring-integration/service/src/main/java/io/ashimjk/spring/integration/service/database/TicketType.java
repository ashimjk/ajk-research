package io.ashimjk.spring.integration.service.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ticket_types")
public class TicketType {

    @Id
    @Column(name = "ticket_type_code", unique = true, nullable = false, length = 1)
    private String code;

    @Column(name = "ticket_type_name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "includes_workshop", nullable = false)
    private boolean includesWorkshop;

    public TicketType() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncludesWorkshop() {
        return includesWorkshop;
    }

    public void setIncludesWorkshop(boolean includesWorkshop) {
        this.includesWorkshop = includesWorkshop;
    }

    @Override
    public String toString() {
        return code + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
