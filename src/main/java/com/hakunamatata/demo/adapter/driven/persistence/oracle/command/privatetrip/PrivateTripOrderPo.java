package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;
import com.hakunamatata.demo.common.utils.DefaultDateTimeFormatter;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "t_private_trip_order")
public class PrivateTripOrderPo extends PersistenceObject<Long> {

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "owner_id")
    private String ownerId;

    public static PrivateTripOrderPo of(PrivateTripOrder order) {
        PrivateTripOrderPo po = new PrivateTripOrderPo();
        po.id = order.getId();
        po.ownerId = order.getOwnerId();
        po.totalAmount = order.getTotalAmount();
        po.createAt = order.getCreateAt().format(DefaultDateTimeFormatter.getFormatter());
        return po;
    }

    public PrivateTripOrder toDomain() {
        return PrivateTripOrder.builder()
                .id(this.id)
                .ownerId(this.ownerId)
                .totalAmount(this.totalAmount)
                .build();
    }
}
