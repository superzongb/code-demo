package com.hakunamatata.demo.adapter.driving.rest;

import com.hakunamatata.demo.application.usecase.command.PrivateTripChangeCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/enterprise/{enterprise-id}/private-orders/{oid}/changes/{cid}/payment", produces = APPLICATION_JSON_VALUE)
public class ChangePaymentResource {

    private final PrivateTripChangeCmd privateTripChangeCmd;

    @PostMapping
    ResponseEntity<?> post(@PathVariable("enterprise-id") String enterpriseId,
                           @PathVariable("oid") long orderId,
                           @PathVariable("cid") long changeId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(privateTripChangeCmd.pay(enterpriseId, orderId, changeId));
    }
}
