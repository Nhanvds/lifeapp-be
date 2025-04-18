package com.mad.lifeapp.component;

import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.exception.ParserTokenException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {


    @Value("${jwt.valid-duration}")
    private Long expiration;

    @Value("${jwt.secret}")
    public String secretKey;

    public String generateToken(UserEntity user) throws JOSEException {
        String email = user.getEmail();
        Long userId = user.getId();
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("issuer_ptit")
                .issueTime(new Date())
                .expirationTime(new Date(
                        System.currentTimeMillis() + expiration
                ))
                .claim("user_id", userId)
                .claim("scope", user.getRole())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(secretKey.getBytes()));
        return jwsObject.serialize();
    }

    public SignedJWT decodedJWT(String token) throws ParserTokenException {
        try {
            token = token.substring(7);
            return SignedJWT.parse(token);
        } catch (ParseException ex) {
            throw new ParserTokenException();
        }

    }


    public Long getUserId(String token) throws ParserTokenException {
        try {
            SignedJWT decodedJWT = decodedJWT(token);
            JWTClaimsSet jwtClaimsSet = decodedJWT.getJWTClaimsSet();
            return jwtClaimsSet.getLongClaim("user_id");
        } catch (ParseException ex) {
            throw new ParserTokenException();
        }
    }




    public String getEmail(String token) throws ParserTokenException {
        try {
            SignedJWT decodedJWT = decodedJWT(token);
            return decodedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException ex) {
            throw new ParserTokenException();
        }

    }

}