package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.DeviceTokenRequest;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.service.DeviceTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device-token")
@RequiredArgsConstructor
public class DeviceTokenController {

    private final DeviceTokenService deviceTokenService;


    @PostMapping("/register")
    public ResponseEntity<?> updateUserForToken(@RequestHeader("Authorization") String token,@RequestBody DeviceTokenRequest request) throws NotFoundException, ParserTokenException {
        deviceTokenService.saveOrUpdateToken(token, request);
        return ResponseEntity.ok("Token updated successfully");
    }

    @DeleteMapping("/{device_token}")
    public ResponseEntity<?> deleteToken(@PathVariable("device_token") String token) throws NotFoundException {
        deviceTokenService.deleteToken(token);
        return ResponseEntity.ok().body("Deleted token successfully");
    }
}

