package com.hakunamatata.demo.application.dto;

import lombok.Data;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@Data
public class FlightDto {

    private final String price;

    private final String flightCode;

    private final String date;


    private final String airlineCorp;
}
