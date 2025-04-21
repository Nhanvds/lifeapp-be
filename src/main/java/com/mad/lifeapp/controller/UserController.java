package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.request.PasswordRequest;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws NotFoundException, InvalidException {
        return new ResponseEntity<UserResponse>(userService.register(userRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserRequest userRequest) throws InvalidCredentialsException, NotFoundException, JOSEException {
        return new ResponseEntity<>(userService.login(userRequest), HttpStatus.OK);
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<UserResponse> updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody UserRequest userRequest) throws ParserTokenException {
        return ResponseEntity.ok().body(userService.updateUserInfo(token,userRequest));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordRequest passwordRequest) throws ParserTokenException {
        userService.changePassword(passwordRequest, token);
        return ResponseEntity.ok().body("Change your password successfully!");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestHeader("Authorization") String token, @RequestBody PasswordRequest passwordRequest) throws ParserTokenException {
        return ResponseEntity.ok().body(userService.resetPassword(token, passwordRequest));
    }


}
