package io.ashimjk.spring.integration.repository;

import io.ashimjk.spring.integration.domain.Beneficiary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends MongoRepository<Beneficiary, String> {

    List<Beneficiary> findAllByOrderByTimestamp(Pageable pageable);

}
