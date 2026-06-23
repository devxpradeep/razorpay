package com.clone.razorpay.payment.service.impl;

import com.clone.razorpay.payment.dto.request.CreateOrderRequest;
import com.clone.razorpay.payment.dto.response.CreateOrderResponse;
import com.clone.razorpay.payment.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        return null;
    }
}
