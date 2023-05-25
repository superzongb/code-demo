package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepo extends JpaRepository<PaymentPo, Long> {
}
