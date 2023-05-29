package com.hakunamatata.demo.adapter.driving.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/enterprise/{enterprise-id}/private-orders/{oid}/changes/{cid}", produces = APPLICATION_JSON_VALUE)
public class SubChangeResource {
}
