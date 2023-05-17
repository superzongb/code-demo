package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PrivateTripOrderRepositoryImpl implements PrivateTripOrderRepository {

    private final JpaPrivateTripOrderRepo jpaPrivateTripOrderRepo;

    @Override
    public Optional<PrivateTripOrder> findById(long orderId) {
        //这里考虑的数据库如何查询到指定的数据。

        PrivateTripOrderPo orderPo = jpaPrivateTripOrderRepo.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        return Optional.of(orderPo.toDomain());
    }

    @Override
    public List<PrivateTripOrder> findPaymentOngoingOrders(int limit, int offset) {
        return null;
    }

    @Override
    public void save(PrivateTripOrder order) {

    }
}
