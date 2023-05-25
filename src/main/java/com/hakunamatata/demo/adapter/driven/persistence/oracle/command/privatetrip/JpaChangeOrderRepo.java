package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaChangeOrderRepo extends JpaRepository<ChangeOrderPo, Long> {
    List<ChangeOrderPo> findByTripOrderId(Long tripOrderId);
}
