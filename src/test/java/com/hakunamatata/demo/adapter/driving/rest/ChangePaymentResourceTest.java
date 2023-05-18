package com.hakunamatata.demo.adapter.driving.rest;

import com.hakunamatata.demo.application.dto.PaymentDto;
import com.hakunamatata.demo.application.usecase.command.PrivateTripChangeCmd;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ChangePaymentResourceTest extends ResourceTest {

    @MockBean
    private PrivateTripChangeCmd privateTripChangeCmd;

    @Nested
    class post {
        @Test
        void given_a_waiting_for_pay_change_order_when_pay_then_return_payment() {

            String enterpriseId = "e001";
            Long orderId = 1l;
            Long changeId = 1L;

            PaymentDto paymentDto = new PaymentDto(1L, LocalDateTime.now(), "AliPay", new BigDecimal("1000.99"),
                    "https://www.alipay.com/payments/xyz");

            BDDMockito.given(privateTripChangeCmd.pay(enterpriseId, orderId, changeId)).willReturn(paymentDto);

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .post(String.format("/enterprise/%s/private-orders/%d/changes/%d/payment", enterpriseId, orderId, changeId))
                    .then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("id", is(1))
                    .body("paymentUrl", containsString("www.alipay.com"));
        }
    }

}