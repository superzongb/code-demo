package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.AirlineBookingService;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Passenger;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.IllegalOrderStateException;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.OrderState;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/10
 * @description:
 */
class PrivateTripServiceTest {
    public static final String passengerId = "xxxxxxxxxxxxxx";
    private PrivateTripService privateTripService;

    private PrivateTripOrderRepository orderRepository;

    private PurchaseServiceRepository purchaseServiceRepository;

    private PurchaseService purchaseService;

    private AirlineBookingService airlineBookingService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(PrivateTripOrderRepository.class);
        purchaseService = spy(PurchaseService.class);
        airlineBookingService = spy(AirlineBookingService.class);
        purchaseServiceRepository = mock(PurchaseServiceRepository.class);

        privateTripService = new PrivateTripService(orderRepository, purchaseServiceRepository);
    }

    @NotNull
    private PrivateTripOrder createTestOrder() {
        PrivateTripOrder order = PrivateTripOrder.builder()
                .id(1L)
                .ownerId("user")
                .description("test order")
                .build();
        try {
            order.submit();
        } catch (IllegalOrderStateException e) {
            throw new RuntimeException(e);
        }
        Flight flight = Flight.builder()
                .flightCode("CA3990")
                .price(BigDecimal.valueOf(1000L))
                .date("2023-04-20")
                .service(airlineBookingService)
                .build();
        Passenger passenger = Passenger.builder()
                .id(passengerId)
                .name("user")
                .build();
        order.addTrip(flight, passenger);
        return order;
    }

    @NotNull
    private PrivateTripOrder makeOrderPaymentOngoing() {
        PrivateTripOrder order = createTestOrder();
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));
        Payment payment = Payment.builder()
                .id(2L)
                .amount(BigDecimal.valueOf(1000L))
                .createAt(LocalDateTime.now())
                .purchaseType("pay")
                .description("test order")
                .build();
        given(purchaseServiceRepository.findByType(anyString())).willReturn(purchaseService);
        willReturn(payment).given(purchaseService).pay(anyLong(), any(), any());
        try {
            order.pay(purchaseServiceRepository, "AliPay");
        } catch (IllegalOrderStateException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Nested
    class pay {

        @Test
        void given_a_normal_order_when_pay_then_pay_succeed() throws IllegalAccessException, IllegalOrderStateException {
            //given
            PrivateTripOrder order = createTestOrder();
            given(orderRepository.findById(1L)).willReturn(Optional.of(order));
            Payment expectPayment = mock(Payment.class);
            given(purchaseServiceRepository.findByType(eq("AliPay"))).willReturn(purchaseService);
            willReturn(expectPayment).given(purchaseService).pay(anyLong(), any(), any());

            //when
            Payment payment = privateTripService.pay(1L, "user", "AliPay");

            //then
            assertThat(payment).isEqualTo(expectPayment);
            assertThat(order.getState()).isEqualTo(OrderState.PAYMENT_ONGOING);
            verify(purchaseService, times(1))
                    .pay(eq(1L), eq(BigDecimal.valueOf(1000L)), eq("test order"));

        }

        @Test
        void given_an_order_belongs_to_other_when_pay_then_illegal_access() {
            // given
            PrivateTripOrder order = createTestOrder();
            given(orderRepository.findById(1L)).willReturn(Optional.of(order));

            // when & then
            assertThatThrownBy(() -> privateTripService.pay(1L, "other", "pay"))
                    .isInstanceOf(IllegalAccessException.class);
        }

        @Test
        void given_a_payment_ongoing_order_when_pay_then_throw_illegal_state() {
            PrivateTripOrder order = makeOrderPaymentOngoing();

            assertThatThrownBy(() -> privateTripService.pay(1L, "user", "pay"))
                    .isInstanceOf(IllegalOrderStateException.class)
                    .hasMessageContaining("Cannot pay.");
        }


    }

    @Nested
    class paid {

        @Test
        void given_a_payment_ongoing_order_when_confirm_then_succeed() throws IllegalOrderStateException {
            // given
            PrivateTripOrder order = makeOrderPaymentOngoing();
            PaymentConfirmation confirmation = PaymentConfirmation.builder()
                    .id(3L)
                    .orderId(1L)
                    .paymentId(2L)
                    .amount(BigDecimal.valueOf(1000L))
                    .createAt(LocalDateTime.now())
                    .confirmType(ConfirmType.SUCCESS)
                    .build();

            // when
            privateTripService.paid(1L, confirmation);

            // then
            assertThat(order.getState()).isSameAs(OrderState.PAID);
            verify(airlineBookingService, times(1))
                    .book(eq("CA3990"), eq("2023-04-20"), eq(BigDecimal.valueOf(1000L)), eq(passengerId));
        }

        @Test
        void given_a_payment_ongoing_order_when_confirm_with_balance_insufficient_then_order_can_be_pay_again() throws IllegalOrderStateException {
            // given
            PrivateTripOrder order = makeOrderPaymentOngoing();
            PaymentConfirmation confirmation = PaymentConfirmation.builder()
                    .id(3L)
                    .orderId(1L)
                    .paymentId(2L)
                    .amount(BigDecimal.valueOf(1000L))
                    .createAt(LocalDateTime.now())
                    .confirmType(ConfirmType.BALANCE_INSUFFICIENT)
                    .build();

            // when
            privateTripService.paid(1L, confirmation);

            // then
            assertThat(order.getState()).isSameAs(OrderState.WAITING_FOR_PAY);
            verify(airlineBookingService, times(0))
                    .book(any(), any(), any(), any());
        }
    }
}