package com.hakunamatata.demo.adapter.driven.persistence.oracle.command.privatetrip;

import com.hakunamatata.demo.adapter.driven.persistence.oracle.command.PersistenceObject;
import com.hakunamatata.demo.common.utils.DefaultDateTimeFormatter;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "t_payment")
public class PaymentPo extends PersistenceObject<Long> {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "purchase_type")
    private String purchaseType;

    @Column(name = "payment_url")
    private String paymentUrl;


    public static PaymentPo of(Payment p, long orderId) {
        PaymentPo po = new PaymentPo();
        po.id = p.getId();
        po.orderId = orderId;
        po.createAt = p.getCreateAt().format(DefaultDateTimeFormatter.getFormatter());
        po.amount = p.getAmount();
        po.purchaseType = p.getPurchaseType();
        po.paymentUrl = p.getPaymentUrl();
        return po;
    }
}
