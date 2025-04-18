package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/regiter")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws NotFoundException, InvalidException {
        return new ResponseEntity<UserResponse>(userService.register(userRequest),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserRequest userRequest) throws InvalidCredentialsException, NotFoundException, JOSEException {
        return new ResponseEntity<>(userService.login(userRequest),HttpStatus.OK);
    }


}
