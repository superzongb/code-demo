package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.application.dto.FlightDto;
import com.hakunamatata.demo.domain.context.privatetrip.SingleTripService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineBookingService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineCorpRepository;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.UnsupportedAirlineCorpException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@Component
@RequiredArgsConstructor
public class EditTrip {

    private final SingleTripService tripService;

    private final AirlineCorpRepository airlineCorpRepository;

    @Transactional
    public Long changeTrip(String enterpriseId, Long orderId, Long tripId, FlightDto newFlight) {
        AirlineBookingService service = airlineCorpRepository.findByCropCode(newFlight.getAirlineCorp())
                .orElseThrow(() -> new UnsupportedAirlineCorpException(newFlight.getAirlineCorp()));

        Flight flight = Flight.builder()
                .flightCode(newFlight.getFlightCode())
                .date(newFlight.getDate())
                .price(new BigDecimal(newFlight.getPrice()))
                .service(service)
                .build();

        return tripService.changeToNewFlight(enterpriseId, orderId, tripId, flight);
    }
}
