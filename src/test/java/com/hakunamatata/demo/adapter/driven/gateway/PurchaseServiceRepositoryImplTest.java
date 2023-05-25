package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.NotSupportedPurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PurchaseServiceRepositoryImplTest {

    private PurchaseServiceRepositoryImpl purchaseServiceRepository;

    @BeforeEach
    void setUp() {
        purchaseServiceRepository = new PurchaseServiceRepositoryImpl();
    }

    @Test
    void given_exist_service_when_find_by_type_then_success() {
        //given
        PurchaseService purchaseService = mock(PurchaseService.class);
        String purchaseType = "AliPay";
        given(purchaseService.getType()).willReturn(purchaseType);
        purchaseServiceRepository.register(purchaseService);

        //when
        PurchaseService result = purchaseServiceRepository.findByType(purchaseType);

        //then
        assertThat(result).isEqualTo(purchaseService);
    }

    @Test
    void given_not_exist_service_when_find_by_type_then_throw_not_supported() {
        //given
        String purchaseType = "AliPay";

        //when & then
        assertThatThrownBy(() -> {
            purchaseServiceRepository.findByType(purchaseType);
        })
                .isInstanceOf(NotSupportedPurchaseService.class)
                .hasMessageContaining(purchaseType);
    }
}