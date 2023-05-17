package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface JpaPrivateTripOrderRepo extends JpaRepository<PrivateTripOrderPo, Long> {

}
