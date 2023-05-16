package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.order.PrivateTripOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@Service
@RequiredArgsConstructor
public class SingleTripService {

    private final PrivateTripOrderRepository privateTripOrderRepository;

    public Long changeToNewFlight(String enterpriseId, Long orderId, Long tripId, Flight newFlight) {
        PrivateTripOrder order = privateTripOrderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Long changeId = order.change(tripId, newFlight);
        privateTripOrderRepository.save(order);
        return changeId;
    }
}
