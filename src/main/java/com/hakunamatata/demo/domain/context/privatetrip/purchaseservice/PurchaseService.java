package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import java.math.BigDecimal;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public interface PurchaseService {
    Payment pay(long orderId, BigDecimal amount, String description);
}
