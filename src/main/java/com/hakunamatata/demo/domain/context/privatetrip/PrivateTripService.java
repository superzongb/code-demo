package com.hakunamatata.demo.domain.context.privatetrip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Passenger;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.IllegalOrderStateException;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrder;
import com.hakunamatata.demo.domain.context.privatetrip.privatetriporder.PrivateTripOrderRepository;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PaymentConfirmation;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/22
 * @description:
 */
@RequiredArgsConstructor
public class PrivateTripService {

    private final PrivateTripOrderRepository orderRepository;

    private final PurchaseServiceRepository purchaseServiceRepository;

    public Payment pay(long orderId, String ownerId, String purchaseType) throws IllegalAccessException, IllegalOrderStateException {
        PrivateTripOrder order = orderRepository.findById(orderId)
                .orElseThrow(EntityExistsException::new);

        if (!order.getOwnerId().equals(ownerId)) {
            throw new IllegalAccessException();
        }

        order.pay(purchaseServiceRepository, purchaseType);

        orderRepository.save(order);

        return order.getPayment();
    }

    public void paid(long orderId, PaymentConfirmation confirmation) throws IllegalOrderStateException {
        PrivateTripOrder order = orderRepository.findById(orderId)
                .orElseThrow(EntityExistsException::new);


        order.paid(confirmation);


        orderRepository.save(order);
    }

    public void placeOrder(List<Pair<Flight, Passenger>> airlines, String userId) {
        PrivateTripOrder order = PrivateTripOrder.builder()
                .id(1L)
                .ownerId(userId)
                .build();
        airlines.forEach(flightPassengerPair -> order.addTrip(flightPassengerPair.getFirst(), flightPassengerPair.getSecond()));
        orderRepository.save(order);
    }
}
