package com.hakunamatata.demo.domain.context.privatetrip.airlineservice;

import com.hakunamatata.demo.domain.core.exception.DomainRuntimeException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
public class UnsupportedAirlineCorpException extends DomainRuntimeException {
    public UnsupportedAirlineCorpException(String message) {
        super("Unsupported Airline Crop: " + message);
    }
}
