package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.trip.ChangeOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrivateTripOrderRepositoryImpl implements PrivateTripOrderRepository {

    private final JpaPrivateTripOrderRepo jpaPrivateTripOrderRepo;

    private final JpaChangeOrderRepo jpaChangeOrderRepo;

    private final JpaPaymentRepo jpaPaymentRepo;

    @Override
    public Optional<PrivateTripOrder> findById(long orderId) {
        //这里考虑的数据库如何查询到指定的数据。

        PrivateTripOrderPo orderPo = jpaPrivateTripOrderRepo.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        List<ChangeOrder> changes = jpaChangeOrderRepo.findByTripOrderId(orderPo.getId()).stream()
                .map(c -> {
                    List<PaymentPo> paymentPos = jpaPaymentRepo.findByOrderId(c.getId());
                    return c.toDomain(paymentPos.stream().map(PaymentPo::toDomain).collect(Collectors.toList()));
                })
                .collect(Collectors.toList());


        return Optional.of(orderPo.toDomain(changes, null));
    }

    @Override
    public List<PrivateTripOrder> findPaymentOngoingOrders(int limit, int offset) {
        return null;
    }

    @Override
    public void save(PrivateTripOrder order) {
        jpaPaymentRepo.saveAll(order.getChanges().parallelStream()
                .map(change ->
                        change.getPayments().stream()
                                .map(p -> PaymentPo.of(p, change.getId()))
                                .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));


        jpaChangeOrderRepo.saveAll(order.getChanges().stream()
                .map(c -> ChangeOrderPo.of(c, order.getId()))
                .collect(Collectors.toList()));

        jpaPrivateTripOrderRepo.save(PrivateTripOrderPo.of(order));
    }
}
