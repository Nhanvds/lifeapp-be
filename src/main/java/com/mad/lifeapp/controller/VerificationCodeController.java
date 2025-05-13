package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.VerificationCodeRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.service.VerificationCodeService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/verifications")
@RequiredArgsConstructor
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;

    @PostMapping("/register/send")
    public ResponseEntity<?> sendRegistrationVerificationCode(@RequestBody String email) throws InvalidException {
        log.info("email: ",email);
        verificationCodeService.createVerification(email, true);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

    @PostMapping("/forget-password/send")
    public ResponseEntity<?> sendForgetPasswordVerificationCode(@RequestBody String email) throws InvalidException {
        verificationCodeService.createVerification(email, false);
        return new ResponseEntity<>("Successfully", HttpStatus.OK);
    }

    @PostMapping("/verify-forget-password")
    public ResponseEntity<TokenResponse> verifyCode(@RequestBody VerificationCodeRequest verificationCodeRequest) throws InvalidException, NotFoundException, ParserTokenException, JOSEException {
        return ResponseEntity.ok().body(verificationCodeService.verifyForgetPassword(verificationCodeRequest));
    }

}
