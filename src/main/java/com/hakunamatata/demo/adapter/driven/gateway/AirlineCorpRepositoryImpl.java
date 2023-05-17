package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineBookingService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineCorpRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AirlineCorpRepositoryImpl implements AirlineCorpRepository {
    @Override
    public Optional<AirlineBookingService> findByCropCode(String code) {
        return Optional.empty();
    }
}
