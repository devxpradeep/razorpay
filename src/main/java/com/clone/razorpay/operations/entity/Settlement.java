package com.clone.razorpay.operations.entity;

import com.clone.razorpay.common.entity.Money;
import com.clone.razorpay.common.enums.SettlementStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlement")
public class Settlement {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "gross_amount_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gross_amount_units", nullable = false))
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "refund_amount_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "refund_amount_units", nullable = false))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "fee_amount_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "fee_amount_units", nullable = false))
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "gst_amount_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gst_amount_units", nullable = false))
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "net_amount_currency", nullable = false)),
            @AttributeOverride(name = "amountUnits", column = @Column(name = "net_amount_units", nullable = false))
    })
    private Money netAmount;

    @Column(length = 200)
    private String bankReference;

    private LocalDateTime processedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private SettlementStatus status;
}
