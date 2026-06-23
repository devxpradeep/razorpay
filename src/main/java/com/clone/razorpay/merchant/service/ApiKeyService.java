package com.clone.razorpay.merchant.service;

import com.clone.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.clone.razorpay.merchant.dto.response.ApiKeyResponse;
import com.clone.razorpay.merchant.dto.response.CreateApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    CreateApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    CreateApiKeyResponse rotate(UUID merchantId, UUID keyId);
}
