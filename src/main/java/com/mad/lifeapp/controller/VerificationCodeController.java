package com.mad.lifeapp.controller;

import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifications")
@RequiredArgsConstructor
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public ResponseEntity<?> sendVerificationCode(@RequestBody String email) throws InvalidException {
        verificationCodeService.createVerification(email);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

}
