package com.clone.razorpay.merchant.dto.response;

import com.clone.razorpay.common.enums.Environment;

import java.util.UUID;

public record CreateApiKeyResponse(
        UUID id,
        String keyId,
        String secret,
        Environment environment
) {
}
