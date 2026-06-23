package com.clone.razorpay.merchant.dto.request;

import com.clone.razorpay.common.enums.Environment;
import jakarta.validation.constraints.NotNull;

public record CreateApiKeyRequest(
        @NotNull
        Environment environment
) {
}
