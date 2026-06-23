package com.clone.razorpay.merchant.controller;

import com.clone.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.clone.razorpay.merchant.dto.response.MerchantSignupResponse;
import com.clone.razorpay.merchant.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<MerchantSignupResponse> signUp(@Valid @RequestBody MerchantSignupRequest request) {
        MerchantSignupResponse response = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
