package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrivateTripOrderRepositoryImplTest {
    @Autowired
    private PrivateTripOrderRepositoryImpl privateTripOrderRepository;

    @Autowired
    private JpaPrivateTripOrderRepo jpaPrivateTripOrderRepo;

    @Autowired
    private JpaChangeOrderRepo jpaChangeOrderRepo;

    @Autowired
    private JpaPaymentRepo jpaPaymentRepo;

    @AfterEach
    void tearDown() {
        jpaPrivateTripOrderRepo.deleteAll();
        jpaChangeOrderRepo.deleteAll();
        jpaPaymentRepo.deleteAll();
    }

    @Nested
    class findById {

        @Test
        @Sql("/test/dbscript/PrivateTripOrderTestData.sql")
        void given_existed_record_in_db_when_find_then_success() {
            PrivateTripOrder order = privateTripOrderRepository.findById(1L)
                    .get();

            assertThat(order).isNotNull();
            assertThat(order.getOwnerId()).isEqualTo("1");
            assertThat(order.getChanges().size()).isEqualTo(2);

        }
    }

    @Nested
    class save {
        @Test
        void given_private_trip_order_with_changes_when_save_then_success() {
            Payment payment = Payment.builder()
                    .id(3L)
                    .purchaseType("AliPay")
                    .createAt(LocalDateTime.of(2023, 5, 23, 13, 15, 15))
                    .amount(new BigDecimal("100.99"))
                    .paymentUrl("https://www.alipay.com/payments/xyz")
                    .build();

            ChangeOrder change = ChangeOrder.builder()
                    .id(2L)
                    .ownerId("Tom Cat")
                    .totalAmount(new BigDecimal("100.99"))
                    .createAt(LocalDateTime.of(2023, 5, 23, 13, 15, 0))
                    .payments(Collections.singletonList(payment))
                    .build();

            PrivateTripOrder order = PrivateTripOrder.builder()
                    .id(1L)
                    .changes(Collections.singletonList(change))
                    .totalAmount(new BigDecimal("1000.99"))
                    .createAt(LocalDateTime.of(2023, 5, 23, 12, 0, 0))
                    .ownerId("Tom Cat")
                    .build();

            privateTripOrderRepository.save(order);

            PrivateTripOrderPo orderPo = jpaPrivateTripOrderRepo.findById(order.getId()).get();
            ChangeOrderPo changePo = jpaChangeOrderRepo.findById(change.getId()).get();
            PaymentPo paymentPo = jpaPaymentRepo.findById(payment.getId()).get();

            assertThat(orderPo).isNotNull();
            assertThat(orderPo.getTotalAmount()).isEqualTo(new BigDecimal("1000.99"));
            assertThat(orderPo.getCreateAt()).isEqualTo("2023/05/23 12:00:00");

            assertThat(changePo).isNotNull();
            assertThat(changePo.getTotalAmount()).isEqualTo(new BigDecimal("100.99"));
            assertThat(changePo.getTripOrderId()).isEqualTo(orderPo.getId());

            assertThat(paymentPo.getAmount()).isEqualTo(new BigDecimal("100.99"));
            assertThat(paymentPo.getPaymentUrl()).contains("www.alipay.com");

            assertThat(jpaChangeOrderRepo.findByTripOrderId(orderPo.getId()).size()).isEqualTo(1);
        }
    }
}