package io.ashimjk.spring.integration.service.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PricingCategoryRepository extends JpaRepository<PricingCategory, String> {

    Optional<PricingCategory> findByCode(String code);

    @Query("from PricingCategory pc where pc.startDate <= :date and pc.endDate > :date")
    Optional<PricingCategory> findByDate(@Param("date") LocalDate date);

}
