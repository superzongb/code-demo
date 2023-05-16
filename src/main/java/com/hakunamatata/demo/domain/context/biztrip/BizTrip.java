package com.hakunamatata.demo.domain.context.biztrip;

import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/10
 * @description:
 */
@SuperBuilder
public class BizTrip {

    private final BigDecimal amount;

    public BigDecimal getAmount() {
        return this.amount;
    }
}
