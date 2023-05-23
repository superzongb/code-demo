package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ChangeServiceTest {

    private ChangeService changeService;

    private PrivateTripOrderRepository privateTripOrderRepository;

    private PurchaseServiceRepository purchaseServiceRepository;

    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        privateTripOrderRepository = mock(PrivateTripOrderRepository.class);
        purchaseServiceRepository = mock(PurchaseServiceRepository.class);
        purchaseService = spy(PurchaseService.class);
        changeService = new ChangeService(privateTripOrderRepository, purchaseServiceRepository);
    }

    @Nested
    class payForChange {

        @Test
        void given_private_trip_order_with_change_when_pay_for_change_then_get_payment() {
            String enterPriseId = "e001";
            long orderId = 1L;
            long changeId = 1L;
            String purchaseType = "AliPay";


            //given
            ChangeOrder changeOrder = ChangeOrder.builder()
                    .id(1L)
                    .totalAmount(new BigDecimal("1000.99"))
                    .build();

            PrivateTripOrder order = PrivateTripOrder.builder()
                    .id(1L)
                    .changes(Arrays.asList(changeOrder))
                    .build();

            given(privateTripOrderRepository.findById(1L)).willReturn(Optional.of(order));
            given(purchaseService.pay(any(), any(), any())).willReturn(mock(Payment.class));
            given(purchaseServiceRepository.findByType(eq("AliPay"))).willReturn(purchaseService);
            Payment payment = changeService.payForChange(enterPriseId, orderId, changeId, purchaseType);

            assertThat(payment).isNotNull();
            verify(purchaseService).pay(eq(1L), eq(changeOrder.getTotalAmount()), any());
        }
    }

}