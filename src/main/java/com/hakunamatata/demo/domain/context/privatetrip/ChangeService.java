package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ChangeService {

    private final PrivateTripOrderRepository privateTripOrderRepository;

    private final PurchaseServiceRepository purchaseServiceRepository;

    public Payment payForChange(String enterPriseId, Long orderId, Long changeId, String purchaseType) {
        PrivateTripOrder order = privateTripOrderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        ChangeOrder changeOrder = order.getChanges().stream()
                .filter(c -> changeId.equals(c.getId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

        changeOrder.pay(purchaseServiceRepository, purchaseType);

        privateTripOrderRepository.save(order);

        return changeOrder.getPayment();
    }
}
