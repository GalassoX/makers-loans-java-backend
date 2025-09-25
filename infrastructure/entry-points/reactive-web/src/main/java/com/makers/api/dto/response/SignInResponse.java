package com.makers.api.dto.response;

import lombok.Builder;

@Builder
public record SignInResponse(Integer userId) {
}
