package com.clone.razorpay.merchant.service.impl;

import com.clone.razorpay.common.enums.MerchantStatus;
import com.clone.razorpay.common.enums.UserRole;
import com.clone.razorpay.common.exception.DuplicateResourceException;
import com.clone.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.clone.razorpay.merchant.dto.response.MerchantSignupResponse;
import com.clone.razorpay.merchant.entity.AppUser;
import com.clone.razorpay.merchant.entity.Merchant;
import com.clone.razorpay.merchant.repository.AppUserRepository;
import com.clone.razorpay.merchant.repository.MerchantRepository;
import com.clone.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantSignupResponse signUp(MerchantSignupRequest request) {
        if (merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("MERCHANT_ALREADY_EXISTS", "Merchant with email " + request.email() + " already exists");
        }
        Merchant merchant = Merchant.builder()
                .name(request.name())
                .email(request.email())
                .businessName(request.businessName())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING)
                .build();
        merchantRepository.save(merchant);

        // create app user for merchant
        AppUser appUser = AppUser.builder()
                .email(request.email())
                .passwordHash(request.password()) // TODO encrypt password
                .role(UserRole.OWNER)
                .merchant(merchant)
                .build();
        appUserRepository.save(appUser);

        return new MerchantSignupResponse(merchant.getId(), merchant.getName(), merchant.getEmail(), merchant.getBusinessName(), merchant.getBusinessType().name(), merchant.getStatus());
    }
}
