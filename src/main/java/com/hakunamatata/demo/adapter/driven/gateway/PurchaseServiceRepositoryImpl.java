package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceRepositoryImpl implements PurchaseServiceRepository {
    @Override
    public PurchaseService findByType(String type) {
        return null;
    }
}
