package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPaymentRepo extends JpaRepository<PaymentPo, Long> {
    List<PaymentPo> findByOrderId(Long orderId);
}
