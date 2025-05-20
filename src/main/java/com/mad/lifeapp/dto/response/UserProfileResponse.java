package com.mad.lifeapp.dto.response;

import com.mad.lifeapp.enums.UserGenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {

    private Long id;
    private String fullname;
    private String imageUrl;
    private UserGenderEnum gender;
    private Integer age;
    private Float height;
    private Float weight;
}
