package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.repository.VerificationCodeRepository;
import com.mad.lifeapp.service.EmailService;
import com.mad.lifeapp.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final static int CODE_EXPIRATION = 1;
    private final static String EMAIL_SUBJECT = "Mã xác thực tài khoản";
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public VerificationCodeEntity createVerification(String email) throws InvalidException {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // từ 0 đến 9
            sb.append(digit);
        }
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) throw new InvalidException("Email existed");
        Optional<VerificationCodeEntity> verificationCodeEntityOptional = verificationCodeRepository
                .findByEmail(email);
        VerificationCodeEntity verificationCodeEntity = verificationCodeEntityOptional.orElse(new VerificationCodeEntity());
        verificationCodeEntity.setExpiredAt(LocalDateTime.now().plusHours(CODE_EXPIRATION));
        verificationCodeEntity.setEmail(email);
        verificationCodeEntity.setCode(sb.toString());
        verificationCodeRepository.save(verificationCodeEntity);
        emailService.sendEmail(verificationCodeEntity.getEmail(), EMAIL_SUBJECT, verificationCodeEntity.getCode());
        return verificationCodeEntity;
    }


}
