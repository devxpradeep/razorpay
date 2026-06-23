package com.clone.razorpay.merchant.service.impl;

import com.clone.razorpay.common.exception.ResourceNotFoundException;
import com.clone.razorpay.common.util.RandomizerUtil;
import com.clone.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.clone.razorpay.merchant.dto.response.ApiKeyResponse;
import com.clone.razorpay.merchant.dto.response.CreateApiKeyResponse;
import com.clone.razorpay.merchant.entity.ApiKey;
import com.clone.razorpay.merchant.entity.Merchant;
import com.clone.razorpay.merchant.repository.ApiKeyRepository;
import com.clone.razorpay.merchant.repository.MerchantRepository;
import com.clone.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {
    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
    public CreateApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("Merchant", merchantId));

        String keyId = "rzp_" + request.environment().name().toLowerCase() + "_" + RandomizerUtil.randomBase64(24);
        String rawSecretHash = RandomizerUtil.randomBase64(40); //TODO encrypt with bcryptpasswordencoder
        ApiKey apiKey = ApiKey.builder()
                .keyId(keyId)
                .keySecretHash(rawSecretHash)
                .merchant(merchant)
                .environment(request.environment())
                .build();
        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(), keyId, rawSecretHash, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId)
                .stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.getEnabled(),
                        apiKey.getLastUsedAt(),
                        apiKey.getRotatedAt()))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(ak -> ak.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        apiKey.setEnabled(false);
    }

    @Override
    @Transactional
    public CreateApiKeyResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(ak -> ak.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        String newSecretHash = RandomizerUtil.randomBase64(40); //TODO encrypt with bcryptpasswordencoder
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newSecretHash);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiredAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(), apiKey.getKeyId(), newSecretHash, apiKey.getEnvironment());
    }
}
