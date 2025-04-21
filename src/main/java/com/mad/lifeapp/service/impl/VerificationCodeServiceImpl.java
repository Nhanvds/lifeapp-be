package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.VerificationCodeRequest;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.repository.VerificationCodeRepository;
import com.mad.lifeapp.service.EmailService;
import com.mad.lifeapp.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final static int CODE_EXPIRATION = 1;
    private final static String REGISTER_EMAIL_SUBJECT = "Mã xác thực tài khoản";
    private final static String FORGET_PASSWORD_EMAIL_SUBJECT = "Mã xác thực tìm lại tài khoản";
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public VerificationCodeEntity createVerification(String email, boolean isRegistration) throws InvalidException {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // từ 0 đến 9
            sb.append(digit);
        }
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent() && isRegistration) throw new InvalidException("Email existed");
        Optional<VerificationCodeEntity> verificationCodeEntityOptional = verificationCodeRepository
                .findByEmail(email);
        VerificationCodeEntity verificationCodeEntity = verificationCodeEntityOptional.orElse(new VerificationCodeEntity());
        verificationCodeEntity.setExpiredAt(LocalDateTime.now().plusHours(CODE_EXPIRATION));
        verificationCodeEntity.setEmail(email);
        verificationCodeEntity.setCode(sb.toString());
        verificationCodeRepository.save(verificationCodeEntity);
        if (isRegistration) {
            emailService.sendEmail(verificationCodeEntity.getEmail(), REGISTER_EMAIL_SUBJECT, verificationCodeEntity.getCode());
        } else {
            emailService.sendEmail(verificationCodeEntity.getEmail(), FORGET_PASSWORD_EMAIL_SUBJECT, verificationCodeEntity.getCode());
        }
        return verificationCodeEntity;
    }

    @Override
    public boolean verifyForgetPassword(String token, VerificationCodeRequest verificationCodeRequest) throws ParserTokenException, InvalidException, NotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!user.getEmail().equals(verificationCodeRequest.getEmail())) {
            throw new InvalidException("Email invalid");
        }
        VerificationCodeEntity verificationCodeEntity = verificationCodeRepository.findByEmail(verificationCodeRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Verification code not found"));
        if (!verificationCodeEntity.getCode().equals(verificationCodeRequest.getCode())
                || verificationCodeEntity.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new InvalidException("Verification code invalid");
        }
        return true;
    }
}
