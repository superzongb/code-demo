package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

public interface PurchaseServiceRepository {
    PurchaseService findByType(String type);
}
