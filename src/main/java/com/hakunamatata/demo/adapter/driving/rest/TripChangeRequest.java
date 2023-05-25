package com.hakunamatata.demo.adapter.driving.rest;

import com.hakunamatata.demo.application.dto.FlightDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@Data
public class TripChangeRequest {

    @NotNull
    private Long changingTripId;

    @Valid
    @NotNull(message = "Must have new flight.")
    private FlightDto flight;
}
