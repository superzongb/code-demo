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
import java.util.Collections;
import java.util.Optional;

import static com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeState.PAYING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
            long changeId = 3L;
            String purchaseType = "AliPay";


            //given
            ChangeOrder changeOrder = ChangeOrder.builder()
                    .id(changeId)
                    .totalAmount(new BigDecimal("1000.99"))
                    .build();

            PrivateTripOrder order = PrivateTripOrder.builder()
                    .id(orderId)
                    .changes(Collections.singletonList(changeOrder))
                    .build();

            given(privateTripOrderRepository.findById(orderId)).willReturn(Optional.of(order));
            given(purchaseService.pay(any(), any(), any())).willReturn(mock(Payment.class));
            given(purchaseServiceRepository.findByType(eq("AliPay"))).willReturn(purchaseService);

            //when
            Payment payment = changeService.payForChange(enterPriseId, orderId, changeId, purchaseType);

            //then
            assertThat(payment).isNotNull();
            assertThat(changeOrder.getState()).isEqualTo(PAYING);
            verify(purchaseService).pay(eq(changeId), eq(changeOrder.getTotalAmount()), any());
        }

    }
}