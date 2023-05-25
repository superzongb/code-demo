package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.application.dto.FlightDto;
import com.hakunamatata.demo.domain.context.privatetrip.SingleTripService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineBookingService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineCorpRepository;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
class EditTripTest {

    private EditTrip editTrip;

    private AirlineCorpRepository airlineCorpRepository;

    private SingleTripService tripService;

    @BeforeEach
    void setUp() {
        airlineCorpRepository = mock(AirlineCorpRepository.class);
        tripService = mock(SingleTripService.class);
        editTrip = new EditTrip(tripService, airlineCorpRepository);
    }

    @Nested
    class changeTrip {
        @Test
        void given_a_trip_when_change_then_return_change_id() {
            //given
            Long expectedChangeId = 1L;

            given(airlineCorpRepository.findByCropCode("CA"))
                    .willReturn(Optional.of(mock(AirlineBookingService.class)));

            given(tripService.changeToNewFlight(anyString(), anyLong(), anyLong(), any(Flight.class)))
                    .willReturn(expectedChangeId);

            FlightDto flightDto = new FlightDto("1000.99", "CA9977", "2023-04-15", "CA");

            // when
            Long changeId = editTrip.changeTrip("e001", 1L, 1L, flightDto);

            // then
            assertThat(changeId).isEqualTo(expectedChangeId);
        }
    }
}