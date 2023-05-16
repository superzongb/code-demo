package com.hakunamatata.demo.domain.context.privatetrip.order;

import java.util.List;
import java.util.Optional;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public interface PrivateTripOrderRepository {
    Optional<PrivateTripOrder> findById(long orderId);

    List<PrivateTripOrder> findPaymentOngoingOrders(int limit, int offset);

    void save(PrivateTripOrder order);
}
