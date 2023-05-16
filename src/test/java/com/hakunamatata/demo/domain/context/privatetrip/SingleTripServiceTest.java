package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
class SingleTripServiceTest {

    private SingleTripService singleTripService;

    private PrivateTripOrderRepository privateTripOrderRepository;

    @BeforeEach
    void setUp() {
        privateTripOrderRepository = mock(PrivateTripOrderRepository.class);
        singleTripService = new SingleTripService(privateTripOrderRepository);
    }

    @Nested
    class changeToNewFlight{
        @Test
        void given_an_unused_trip_when_change_then_succeed() {
            Flight flight = Flight.builder()
                            .build();
            Trip trip = Trip.builder()
                    .id(1L)
                    .build();
            PrivateTripOrder order = PrivateTripOrder.builder()
                    .id(1L)
                    .trips(Arrays.asList(trip))
                    .build();
            BDDMockito.given(privateTripOrderRepository.findById(1L))
                    .willReturn(Optional.of(order));

            Long changeId = singleTripService.changeToNewFlight("001", 1L, 1L, flight);

            assertThat(changeId).isEqualTo(0L);
            assertThat(order.getChanges().size()).isEqualTo(1);
        }

        @Test
        void given_an_cancelled_trip_when_change_then_failed() {

        }
    }

}