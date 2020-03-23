package io.ashimjk.spring.cloud.webflux.repository;

import io.ashimjk.spring.cloud.webflux.model.Beneficiary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends ReactiveMongoRepository<Beneficiary, String> {
}
