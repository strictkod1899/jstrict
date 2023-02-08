package ru.strict.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import ru.strict.component.Token;
import ru.strict.validate.CommonValidator;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class JWTTokenUtil {

    public static Token createToken(UUID id,
            Date expireTimeAccess,
            Date issuedAt,
            String issuer,
            String subject,
            Date notBefore,
            String audience) {

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        Key key = Keys.secretKeyFor(algorithm);

        JwtBuilder builder = Jwts.builder();

        if (id != null) {
            builder.setId(id.toString());
        }
        if (expireTimeAccess != null) {
            builder.setExpiration(expireTimeAccess);
        }
        if (issuedAt != null) {
            builder.setIssuedAt(issuedAt);
        }
        if (!CommonValidator.isNullOrEmpty(issuer)) {
            builder.setIssuer(issuer);
        }
        if (!CommonValidator.isNullOrEmpty(subject)) {
            builder.setSubject(subject);
        }
        if (notBefore != null) {
            builder.setNotBefore(notBefore);
        }
        if (!CommonValidator.isNullOrEmpty(audience)) {
            builder.setAudience(audience);
        }

        String secret = new String(key.getEncoded());
        String token = builder.signWith(key).compact();

        return new Token(token, secret, algorithm.name());
    }

    public static Jws<Claims> decodeToken(String key, String token) {
        Jws<Claims> result;
        try {
            result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token);
        } catch (JwtException e) {
            result = null;
        }

        return result;
    }
}
