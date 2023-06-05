package com.hakunamatata.demo.domain.context.privatetrip.privatetriporder;

import com.hakunamatata.demo.domain.context.privatetrip.trip.Trip;

import java.util.List;
import java.util.Optional;

public interface TripCollection {
    List<Trip> getTrips();

    Optional<Trip> findTripById(Long id);
}
