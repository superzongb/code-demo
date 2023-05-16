package com.hakunamatata.demo.domain.context.privatetrip.trip;

import java.util.List;
import java.util.Optional;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/22
 * @description:
 */
public interface TripRepository {
    void saveAll(List<Trip> trips);

    List<Trip> findByOrderId(long orderId);

    Optional<Trip> findById(long id);
}
