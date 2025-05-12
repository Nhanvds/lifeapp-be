package com.mad.lifeapp.dto.response;

import com.mad.lifeapp.enums.UserRoleEnum;
import com.mad.lifeapp.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String fullname;
    private String country;
    private UserStatusEnum status;
    private UserRoleEnum role;
    private String googleId;
    private String imageUrl;
}
