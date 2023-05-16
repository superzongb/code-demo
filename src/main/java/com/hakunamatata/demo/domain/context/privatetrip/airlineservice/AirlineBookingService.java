package com.hakunamatata.demo.domain.context.privatetrip.airlineservice;

import java.math.BigDecimal;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public interface AirlineBookingService {
    long book(String flightCode, String date, BigDecimal price, String passengerId);
}
