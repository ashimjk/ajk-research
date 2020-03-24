package io.ashimjk.sse.repository;

import io.ashimjk.sse.domain.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    @Override
    List<Beneficiary> findAll();

}
