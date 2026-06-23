package com.clone.razorpay.merchant.service;

import com.clone.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.clone.razorpay.merchant.dto.response.MerchantSignupResponse;

public interface AuthService {
    MerchantSignupResponse signUp(MerchantSignupRequest request);
}
