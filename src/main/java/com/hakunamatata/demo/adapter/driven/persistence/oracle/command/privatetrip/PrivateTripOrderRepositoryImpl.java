package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrivateTripOrderRepositoryImpl implements PrivateTripOrderRepository {
    @Override
    public Optional<PrivateTripOrder> findById(long orderId) {
        return Optional.empty();
    }

    @Override
    public List<PrivateTripOrder> findPaymentOngoingOrders(int limit, int offset) {
        return null;
    }

    @Override
    public void save(PrivateTripOrder order) {

    }
}
