package com.hakunamatata.demo.domain.context.biztrip;

import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/10
 * @description:
 */

@SuperBuilder
public class BizTripOrder {
    private final Long id;

    private final List<BizTrip> trips = new ArrayList<>();

    private final String ownerId;

    private BizTripApproval approval;

    private String approver;

    private BigDecimal amount = new BigDecimal(0);

    public void addBizTrip(BizTrip trip) {
        this.amount = this.amount.add(trip.getAmount());
    }
}
