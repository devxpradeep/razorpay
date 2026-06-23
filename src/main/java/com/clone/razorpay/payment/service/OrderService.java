package com.clone.razorpay.payment.service;

import com.clone.razorpay.payment.dto.request.CreateOrderRequest;
import com.clone.razorpay.payment.dto.response.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse create(CreateOrderRequest request);
}
