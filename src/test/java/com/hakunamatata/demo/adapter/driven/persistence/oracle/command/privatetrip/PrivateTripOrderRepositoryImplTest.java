package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrivateTripOrderRepositoryImplTest {
    @Autowired
    private PrivateTripOrderRepositoryImpl privateTripOrderRepository;

    @Nested
    class findById {

        @Test
        @Sql("/test/dbscript/PrivateTripOrderTestData.sql")
        void given_existed_record_in_db_when_find_then_success() {
            Optional<PrivateTripOrder> order = privateTripOrderRepository.findById(1L);

            assertThat(order.get()).isNotNull();
            assertThat(order.get().getOwnerId()).isEqualTo("1");
        }
    }
}