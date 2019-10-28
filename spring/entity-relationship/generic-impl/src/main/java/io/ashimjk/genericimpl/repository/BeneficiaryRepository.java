package io.ashimjk.genericimpl.repository;

import io.ashimjk.genericimpl.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
