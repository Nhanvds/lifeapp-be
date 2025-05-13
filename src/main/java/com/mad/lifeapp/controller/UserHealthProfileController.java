package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.UserHealthProfileRequest;
import com.mad.lifeapp.dto.response.UserHealthProfileResponse;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.exception.UserNotFoundException;
import com.mad.lifeapp.service.UserHealthProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-health-profiles")
@RequiredArgsConstructor
public class UserHealthProfileController {
    private final UserHealthProfileService userHealthProfileService;


    /**
     * Tạo mới nếu chưa tồn tại bản ghi trong ngày
     * Cập nhật nếu đã tồn tại một bản ghi trong ngày
     * Tạo mới bảng thông báo nếu người dùng chưa có bảng thông báo
     */
    @PostMapping("/create")
    public ResponseEntity<UserHealthProfileResponse> createUserHealthProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UserHealthProfileRequest userHealthProfileRequest
    ) throws UserNotFoundException, ParserTokenException {
        return ResponseEntity.ok().body(userHealthProfileService.createUserHealthProfile(userHealthProfileRequest, token));
    }

}
