package com.mad.lifeapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String verificationCode;
    private String imageUrl;
    private String country;
    private String username;
}
