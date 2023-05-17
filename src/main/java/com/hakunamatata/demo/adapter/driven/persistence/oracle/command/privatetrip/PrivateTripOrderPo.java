package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_private_trip_order")
public class PrivateTripOrderPo extends PersistenceObject<Long> {

    @Column(name="total_amount")
    private BigDecimal totalAmount;

    @Column(name="owner_id")
    private String ownerId;

    public PrivateTripOrder toDomain() {
        return PrivateTripOrder.builder()
                .id(this.id)
                .ownerId(this.ownerId)
                .totalAmount(this.totalAmount)
                .build();
    }
}
