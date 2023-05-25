package com.hakunamatata.demo.domain.context.privatetrip.airlineservice;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@Getter
@SuperBuilder
public class Flight {
    private final BigDecimal price;

    private final String flightCode;

    private final String date;

    private final AirlineBookingService service;

}
