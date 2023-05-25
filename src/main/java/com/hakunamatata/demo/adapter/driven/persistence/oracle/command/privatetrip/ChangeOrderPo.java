package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "t_change_table")
public class ChangeOrderPo extends PersistenceObject<Long> {
    @Column(name = "trip_order_id")
    private Long tripOrderId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    public static ChangeOrderPo of(ChangeOrder changeOrder, Long tripOrderId) {
        ChangeOrderPo po = new ChangeOrderPo();
        po.id = changeOrder.getId();
        po.tripOrderId = tripOrderId;
        po.totalAmount = changeOrder.getTotalAmount();
        return po;
    }
}
