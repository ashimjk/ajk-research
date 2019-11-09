package io.ashimjk.genericimpl.repository;

import io.ashimjk.genericimpl.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    List<Beneficiary> findByIdTypesExpiryDate(LocalDate localDate);
}
