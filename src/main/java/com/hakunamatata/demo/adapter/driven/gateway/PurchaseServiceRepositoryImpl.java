package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.NotSupportedPurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseServiceRepositoryImpl implements PurchaseServiceRepository {
    private final Map<String, PurchaseService> services = new HashMap<>();

    @Override
    public PurchaseService findByType(String type) {
        return Optional.ofNullable(services.get(type))
                .orElseThrow(() -> new NotSupportedPurchaseService(type));
    }

    @Override
    public void register(PurchaseService service) {
        if (!services.containsKey(service.getType())) {
            services.put(service.getType(), service);
        }
    }
}
