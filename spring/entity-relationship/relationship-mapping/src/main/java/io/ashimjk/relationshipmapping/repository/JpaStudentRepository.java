package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends CrudRepository<Student, Long> {
}
