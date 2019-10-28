package io.ashimjk.genericimpl.sample.repository;

import io.ashimjk.genericimpl.sample.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
}
