package com.makers.api.dto.request;

public record CreateLoan(Integer userId, Integer amount, String paymentDate) {
}
