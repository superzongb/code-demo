package com.hakunamatata.demo.domain.context.privatetrip.trip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Passenger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@SuperBuilder
public class Trip {
    @Getter
    private final long id;

    private final Flight flight;

    private final Passenger passenger;

    public void bookFlight() {
        flight.getService().book(flight.getFlightCode(), flight.getDate(), flight.getPrice(), passenger.getId());
    }

    public void ticketConfirmed(TicketConfirmation ticket) {

    }

    public void cancel() {

    }

    public void cancelled() {

    }
}
