package com.hakunamatata.demo.adapter.driving.rest;

import com.hakunamatata.demo.application.dto.FlightDto;
import com.hakunamatata.demo.application.usecase.command.EditTrip;
import com.hakunamatata.demo.domain.core.exception.EntityNotExistedException;
import com.hakunamatata.demo.domain.core.exception.IllegalEntityStateException;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
class ChangeResourceTest extends ResourceTest {

    @MockBean
    private EditTrip editTrip;

    @Nested
    class post {
        @Test
        void given_a_confirmed_trip_when_change_then_need_to_pay() {
            Long expectChangeId = 1L;
            FlightDto flightDto = new FlightDto("A1000.999", "CA9977", "2023-04-11", "CA");
            BDDMockito.given(editTrip.changeTrip("e001", 1L, 1L, flightDto)).willReturn(expectChangeId);

            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of("flight", ImmutableMap.of("price", "A1000.999",
                            "flightCode", "CA9977",
                            "date", "2023-04-11",
                            "airlineCorp", "CA")))
                    .when()
                    .post("/enterprise/e001/private-orders/1/trips/1/change")
                    .then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("changeId", is(1));
        }

        @Test
        void given_a_not_existed_trip_when_change_then_not_exist() {
            BDDMockito.willThrow(new EntityNotExistedException("Trip not existed."))
                    .given(editTrip).changeTrip(anyString(), anyLong(), anyLong(), any(FlightDto.class));

            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of("flight", ImmutableMap.of("price", "A1000.999",
                            "flightCode", "CA9977",
                            "date", "2023-04-11",
                            "airlineCorp", "CA")))
                    .when()
                    .post("/enterprise/e001/private-orders/1/trips/1/change")
                    .then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .body("message", is("Trip not existed."));
        }

        @Test
        void given_a_refunded_trip_when_change_then_forbidden(){
            BDDMockito.willThrow(new IllegalEntityStateException("Trip has been canceled."))
                    .given(editTrip).changeTrip(anyString(), anyLong(), anyLong(), any(FlightDto.class));

            given()
                    .contentType(ContentType.JSON)
                    .body(ImmutableMap.of("flight", ImmutableMap.of("price", "A1000.999",
                            "flightCode", "CA9977",
                            "date", "2023-04-11",
                            "airlineCorp", "CA")))
                    .when()
                    .post("/enterprise/e001/private-orders/1/trips/1/change")
                    .then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("message", is("Trip has been canceled."));
        }
    }
}