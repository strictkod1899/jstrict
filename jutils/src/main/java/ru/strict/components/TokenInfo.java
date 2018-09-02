package ru.strict.components;

public class TokenInfo {

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
}
