package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_private_trip_order")
public class PrivateTripOrderPo extends PersistenceObject<Long> {
    private String totalAmount;
}
