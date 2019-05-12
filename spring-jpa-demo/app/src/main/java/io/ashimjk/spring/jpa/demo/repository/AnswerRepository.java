package io.ashimjk.spring.jpa.demo.repository;

import io.ashimjk.spring.jpa.demo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	List<Answer> findByQuestionId(Long questionId);

}
