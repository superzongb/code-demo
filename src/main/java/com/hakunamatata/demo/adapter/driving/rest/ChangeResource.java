package com.hakunamatata.demo.adapter.driving.rest;

import com.google.common.collect.ImmutableMap;
import com.hakunamatata.demo.application.usecase.command.EditTrip;
import com.hakunamatata.demo.domain.core.exception.EntityNotExistedException;
import com.hakunamatata.demo.domain.core.exception.IllegalEntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/enterprise/{enterprise-id}/private-orders/{oid}/changes", produces = APPLICATION_JSON_VALUE)
public class ChangeResource {

    private final EditTrip editTrip;

    @PostMapping
    ResponseEntity<?> post(@PathVariable("enterprise-id") String enterpriseId,
                           @PathVariable("oid") long orderId,
                           @RequestBody @Validated TripChangeRequest request) {
        try {
            Long changeId = editTrip.changeTrip(enterpriseId, orderId, request.getChangingTripId(), request.getFlight());
            return ResponseEntity.status(HttpStatus.CREATED).body(ImmutableMap.of("changeId", changeId));
        } catch (EntityNotExistedException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ImmutableMap.of("message", e.getMessage()));
        } catch (IllegalEntityStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ImmutableMap.of("message", e.getMessage()));
        }
    }

}
