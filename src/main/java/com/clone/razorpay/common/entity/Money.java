package com.clone.razorpay.common.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
    private final int amountUnits; // Amount in the smallest currency unit (e.g., cents for USD)
    private final String currency;

    private Money(int i, String currency) {
        this.amountUnits = i;
        this.currency = currency;
    }

    public Money of(int amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public Money inr(int amountInRupees) {
        return new Money(amountInRupees * 100, "INR");
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add money with different currencies");
        }
        return new Money(this.amountUnits + other.amountUnits, this.currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract money with different currencies");
        }
        return new Money(this.amountUnits - other.amountUnits, this.currency);
    }
}
