package io.ashimjk.entityrelationship.repository;

import io.ashimjk.entityrelationship.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
