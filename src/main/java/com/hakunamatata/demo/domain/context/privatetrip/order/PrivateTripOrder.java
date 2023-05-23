package com.hakunamatata.demo.domain.context.privatetrip.order;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Passenger;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.BaseOrder;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.IllegalOrderStateException;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.*;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import com.hakunamatata.demo.domain.context.privatetrip.trip.Trip;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@SuperBuilder
public class PrivateTripOrder extends BaseOrder {

    @Getter
    @Builder.Default
    private OrderState state = OrderState.DRAFT;

    private List<Trip> trips;

    private List<ChangeOrder> changes;


    public void addTrip(Flight flight, Passenger passenger) {
        Optional.ofNullable(trips)
                .orElseGet(() -> {
                    this.trips = new ArrayList<>();
                    return trips;
                })
                .add(Trip.builder()
                        .id(1L)
                        .flight(flight)
                        .passenger(passenger)
                        .build());
        increaseAmount(flight.getPrice());
    }

    @Override
    public void submit() throws IllegalOrderStateException {
        if (state != OrderState.DRAFT) {
            throw new IllegalOrderStateException("Cannot submit.", state);
        }
        super.submit();
        this.state = OrderState.WAITING_FOR_PAY;
    }

    @Override
    public void pay(PurchaseServiceRepository purchaseServiceRepository, String purchaseType) throws IllegalOrderStateException {
        if (state != OrderState.WAITING_FOR_PAY) {
            throw new IllegalOrderStateException("Cannot pay.", state);
        }
        super.pay(purchaseServiceRepository, purchaseType);
        this.state = OrderState.PAYMENT_ONGOING;
    }


    public void paid(PaymentConfirmation confirmation) throws IllegalOrderStateException {
        checkTimeout();

        if (state != OrderState.PAYMENT_ONGOING) {
            throw new IllegalOrderStateException("Cannot paid.", state);
        }

        try {
            super.paid(confirmation);
            trips.parallelStream().forEach(Trip::bookFlight);
            this.state = OrderState.PAID;
        } catch (PaymentBalanceInsufficientException e) {
            this.state = OrderState.WAITING_FOR_PAY;
        } catch (PaymentFailedException e) {
            this.state = OrderState.PAY_FAILED;
        }
    }

    public void bookFlights() {
        if (state != OrderState.PAID) {
            throw new IllegalStateException();
        }

        trips.parallelStream().forEach(Trip::bookFlight);
    }


    public void checkTimeout() {
        if (state == OrderState.PAYMENT_ONGOING) {
            if (isTimeout()) {
                state = OrderState.TIMEOUT;
            }
        }
    }

    public List<Trip> getTrips() {
        return Collections.unmodifiableList(Optional.ofNullable(trips).orElse(Collections.emptyList()));
    }

    public List<ChangeOrder> getChanges() {
        return Collections.unmodifiableList(Optional.ofNullable(changes).orElse(Collections.emptyList()));
    }

    public Long change(Long tripId, Flight newFlight) {
        Trip trip = trips.stream().filter(t -> tripId.equals(t.getId()))
                .findAny()
                .orElseThrow(EntityNotFoundException::new);

        List<ChangeOrder> changes = Optional.ofNullable(this.changes)
                .orElseGet(() -> {
                    this.changes = new ArrayList<>(3);
                    return this.changes;
                });

        ChangeOrder change = ChangeOrder.builder()
                .id(changes.size())
                .ownerId(this.ownerId)
                .cancelTrip(trip)
                .newFlight(newFlight)
                .build();
        changes.add(change);

        return change.getId();

    }

}
