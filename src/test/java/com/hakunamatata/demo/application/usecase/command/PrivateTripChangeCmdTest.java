package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.application.dto.PaymentDto;
import com.hakunamatata.demo.domain.context.privatetrip.ChangeService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PrivateTripChangeCmdTest {

    private ChangeService changeService;

    private PrivateTripChangeCmd privateTripChangeCmd;

    @BeforeEach
    void setUp() {
        changeService = mock(ChangeService.class);
        privateTripChangeCmd = new PrivateTripChangeCmd(changeService);
    }

    @Nested
    class payChangeOrder {
        @Test
        void given_a_waiting_for_pay_change_when_pay_then_get_payment_url() {
            long changeId = 1L;
            long orderId = 1L;
            String enterpriseId = "e001";
            BDDMockito.given(changeService.payForChange(enterpriseId, orderId, changeId))
                    .willReturn(Payment.builder()
                            .id(1L)
                            .amount(new BigDecimal("1000.99"))
                            .purchaseType("AliPay")
                            .createAt(LocalDateTime.now())
                            .paymentUrl("https://www.alipay.com/payments/xyz")
                            .build());

            PaymentDto result = privateTripChangeCmd.payChangeOrder(enterpriseId, orderId, changeId);

            assertThat(result).isNotNull();
            assertThat(result.getPurchaseType()).isEqualTo("AliPay");
            assertThat(result.getPaymentUrl()).contains("www.alipay.com");
            assertThat(result.getAmount()).isEqualTo(new BigDecimal("1000.99"));
        }

    }
}