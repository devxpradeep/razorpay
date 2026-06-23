package com.clone.razorpay.merchant.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String errorCode,
        String errorMessage,
        LocalDateTime timeStamp,
        List<FieldErrors> fieldErrors) {
    public record FieldErrors(
            String field,
            String message) {
    }


    public static ErrorResponse of(String errorCode, String errorMessage) {
        return new ErrorResponse(errorCode, errorMessage, LocalDateTime.now(), null);
    }

    public static ErrorResponse of(String errorCode, String errorMessage, List<FieldErrors> fieldErrors) {
        return new ErrorResponse(errorCode, errorMessage, LocalDateTime.now(), fieldErrors);
    }
}
