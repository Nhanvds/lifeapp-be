package com.mad.lifeapp.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.FirebaseTokenRequest;
import com.mad.lifeapp.dto.request.PasswordRequest;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.exception.*;
import com.mad.lifeapp.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        log.info("test----------");
        return ResponseEntity.ok().body("life app");
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws NotFoundException, InvalidException {
        return new ResponseEntity<UserResponse>(userService.register(userRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserRequest userRequest) throws InvalidCredentialsException, NotFoundException, JOSEException {
        return new ResponseEntity<>(userService.login(userRequest), HttpStatus.OK);
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<UserResponse> updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody UserRequest userRequest) throws ParserTokenException, UserNotFoundException {
        return ResponseEntity.ok().body(userService.updateUserInfo(token,userRequest));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordRequest passwordRequest) throws ParserTokenException, UserNotFoundException {
        userService.changePassword(passwordRequest, token);
        return ResponseEntity.ok().body("Change your password successfully!");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestHeader("Authorization") String token, @RequestBody PasswordRequest passwordRequest) throws ParserTokenException, UserNotFoundException {
        return ResponseEntity.ok().body(userService.resetPassword(token, passwordRequest));
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String token) throws ParserTokenException {
        JwtUtils jwtUtils = new JwtUtils();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtUtils.getUserId(token), null, null );
        return ResponseEntity.ok().body(userService.getUser(token));
    }

    @PostMapping("/login-firebase")
    public ResponseEntity<TokenResponse> loginWithFirebase(@RequestBody FirebaseTokenRequest firebaseTokenRequest) throws FirebaseAuthException, JOSEException {
        return ResponseEntity.ok().body(userService.loginWithFirebase(firebaseTokenRequest));
    }

}
