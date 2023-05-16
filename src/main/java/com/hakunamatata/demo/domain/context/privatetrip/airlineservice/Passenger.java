package com.hakunamatata.demo.domain.context.privatetrip.airlineservice;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@Getter
@SuperBuilder
public class Passenger {
    private final String id;
    private final String name;
}
