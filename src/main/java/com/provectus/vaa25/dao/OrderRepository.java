package com.provectus.vaa25.dao;

import com.provectus.vaa25.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase, Long> {
}
