package com.clone.razorpay.payment.entity;

import com.clone.razorpay.common.entity.Money;
import com.clone.razorpay.common.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_record")
public class OrderRecord {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private final OrderStatus orderStatus = OrderStatus.CREATED;
    @Column(nullable = false)
    private final Integer attempts = 0;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;
    // no FK - cross-service-boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;
    @Embedded
    private Money amount;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

}
