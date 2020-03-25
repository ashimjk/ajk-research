package io.ashimjk.spring.integration.service.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Integer> {

    @Query("from AttendeeTicket at left join fetch at.attendee left join fetch at.ticketPrice left join fetch at.discountCode where at.id = :id")
    Optional<AttendeeTicket> findById(@Param("id") int id);

    @Query("from AttendeeTicket at left join fetch at.attendee left join fetch at.ticketPrice left join fetch at.discountCode where at.ticketCode = :ticketCode")
    Optional<AttendeeTicket> findByTicketCode(@Param("ticketCode") String ticketCode);

}
