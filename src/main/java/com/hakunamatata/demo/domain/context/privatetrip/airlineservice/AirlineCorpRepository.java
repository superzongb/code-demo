package com.hakunamatata.demo.domain.context.privatetrip.airlineservice;

import java.util.Optional;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
public interface AirlineCorpRepository {
    Optional<AirlineBookingService> findByCropCode(String code);
}
