package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.NotSupportedPurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class PurchaseServiceRepositoryImplTest {

    private PurchaseServiceRepositoryImpl purchaseServiceRepository;

    @BeforeEach
    void setUp() {
        purchaseServiceRepository = new PurchaseServiceRepositoryImpl();
    }

    @Test
    void given_exist_service_when_find_by_type_then_success() {
        PurchaseService purchaseService = mock(PurchaseService.class);
        BDDMockito.given(purchaseService.getType()).willReturn("AliPay");
        purchaseServiceRepository.register(purchaseService);
        PurchaseService result = purchaseServiceRepository.findByType("AliPay");
        assertThat(result).isEqualTo(purchaseService);
    }

    @Test
    void given_not_exist_service_when_find_by_type_then_throw_not_supported() {
        assertThatThrownBy(() -> {
            PurchaseService result = purchaseServiceRepository.findByType("AliPay");
        })
                .isInstanceOf(NotSupportedPurchaseService.class)
                .hasMessageContaining("AliPay");
    }
}