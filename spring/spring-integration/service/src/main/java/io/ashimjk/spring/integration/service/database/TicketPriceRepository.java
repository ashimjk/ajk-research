package io.ashimjk.spring.integration.service.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Integer> {

    @Query("from TicketPrice tp left join fetch tp.ticketType left join fetch tp.pricingCategory")
    List<TicketPrice> findAll();

    Optional<TicketPrice> findByTicketTypeAndPricingCategory(TicketType ticketType, PricingCategory pricingCategory);

}
