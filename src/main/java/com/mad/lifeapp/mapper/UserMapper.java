package com.mad.lifeapp.mapper;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
                .fullname(userEntity.getFullname())
                .email(userEntity.getEmail())
                .country(userEntity.getCountry())
                .googleId(userEntity.getGoogleId())
                .role(userEntity.getRole())
                .status(userEntity.getStatus())
                .build();
    }
}
