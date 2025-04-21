package com.mad.lifeapp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationCodeRequest {
    private String email;
    private String code;
}
