package com.clone.razorpay.merchant.dto.response;

import com.clone.razorpay.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantSignupResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        String businessType,
        MerchantStatus merchantStatus
) {
}
