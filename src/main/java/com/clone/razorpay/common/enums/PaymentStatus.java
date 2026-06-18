package com.clone.razorpay.common.enums;

public enum PaymentStatus {
    CREATED,
    AUTHORIZING,
    AUTHORIZED,
    CAPTURING,
    CAPTURED,
    FAILED,
    REFUNDED,
    CANCELLED,
    PARTIALLY_REFUNDED,
    SETTLED,
    AUTH_EXPIRED,
}
