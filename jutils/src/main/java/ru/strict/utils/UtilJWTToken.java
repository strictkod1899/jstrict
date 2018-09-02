package ru.strict.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import ru.strict.components.TokenInfo;
import ru.strict.validates.ValidateBaseValue;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class UtilJWTToken {

    public static TokenInfo createToken(UUID id, Date expireTimeAccess, Date issuedAt,
                                        String issuer, String subject, Date notBefore, String audience){

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        Key key = Keys.secretKeyFor(algorithm);

        JwtBuilder builder = Jwts.builder();

        if(id != null){
            builder.setId(id.toString());
        }
        if(expireTimeAccess != null){
            builder.setExpiration(expireTimeAccess);
        }
        if(issuedAt != null){
            builder.setIssuedAt(issuedAt);
        }
        if(ValidateBaseValue.isNotEmptyOrNull(issuer)){
            builder.setIssuer(issuer);
        }
        if(ValidateBaseValue.isNotEmptyOrNull(subject)){
            builder.setSubject(subject);
        }
        if(notBefore != null){
            builder.setNotBefore(notBefore);
        }
        if(ValidateBaseValue.isNotEmptyOrNull(audience)){
            builder.setAudience(audience);
        }

        String secret = new String(key.getEncoded());
        String token = builder.signWith(key).compact();

        return new TokenInfo(token, secret, algorithm.name());
    }

    public static Jws<Claims> decodeToken(String key, String token){
        Jws<Claims> result;
        try {
            result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token);
        } catch (JwtException e) {
            result = null;
        }

        return result;
    }
}
