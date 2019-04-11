package ru.strict.components;

import java.util.Objects;

public class TokenInfo implements Cloneable {

    private String token;
    private String secret;
    private String algorithm;

    public TokenInfo(String token, String secret, String algorithm) {
        this.token = token;
        this.secret = secret;
        this.algorithm = algorithm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenInfo object = (TokenInfo) o;
        return Objects.equals(token, object.token) &&
                Objects.equals(secret, object.secret) &&
                Objects.equals(algorithm, object.algorithm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, secret, algorithm);
    }

    @Override
    public TokenInfo clone() {
        return new TokenInfo(token, secret, algorithm);
    }
}
