package com.mad.lifeapp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordRequest {
    private String currentPassword;
    private String newPassword;
}
