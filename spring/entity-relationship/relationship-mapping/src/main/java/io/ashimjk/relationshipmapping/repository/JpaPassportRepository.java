package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Passport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPassportRepository extends CrudRepository<Passport, Long> {
}
