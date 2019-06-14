package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * JWT-токен
 */
public class JWTToken<ID> extends Token<ID> {

    /**
     * Издатель токена
     */
    private String issuer;
    /**
     * Назначение токена
     */
    private String subject;
    /**
     * Дата, до которой токен не действителен
     */
    private Date notBefore;
    /**
     * Получатели токена
     */
    private String audience;
    /**
     * Секретный ключ для раскодирования токена
     */
    private String secret;
    /**
     * Алгоритм кодирования токена
     */
    private String algorithm;
    /**
     * Тип токена
     */
    private String type;
    /**
     * Идентификатор пользователя, связанного с данным токеном
     */
    private ID userId;
    /**
     * Пользователь, связанного с данным токеном
     */
    private UserWithToken<ID> user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID userId){
        if(userId == null){
            throw new IllegalArgumentException("userId is NULL");
        }

        this.userId = userId;
        user = null;
    }

    public JWTToken() {
        super();
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        userId = null;
        user = null;
    }

    public JWTToken(String accessToken, String refreshToken, Date expireTimeAccess,
                    Date expireTimeRefresh, Date issuedAt, ID userId) {
        super(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        initialize(userId);
    }

    public JWTToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess,
                    Date expireTimeRefresh, Date issuedAt, ID userId) {
        super(id, accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        initialize(userId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public UserWithToken<ID> getUser() {
        return user;
    }

    public void setUser(UserWithToken<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(UserWithToken<ID> user) {
        setUser(user, false);
    }

    private void setUser(UserWithToken<ID> user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.addTokenSafe(this);
        }
        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("jwt-token [%s]: access - %s, refresh - %s", String.valueOf(getId()),
                getAccessToken(), getRefreshToken());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JWTToken<ID> object = (JWTToken<ID>) o;
        return Objects.equals(issuer, object.issuer) &&
                Objects.equals(subject, object.subject) &&
                Objects.equals(notBefore, object.notBefore) &&
                Objects.equals(audience, object.audience) &&
                Objects.equals(secret, object.secret) &&
                Objects.equals(algorithm, object.algorithm) &&
                Objects.equals(type, object.type) &&
                Objects.equals(userId, object.userId) &&
                Objects.equals(user, object.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCodeWithoutUser(), user);
    }

    @Override
    public JWTToken<ID> clone(){
        JWTToken<ID> clone = (JWTToken<ID>) super.clone();

        clone.setNotBefore(notBefore == null ? null : (Date) notBefore.clone());
        clone.setUser(user == null ? null : user.clone());
        return clone;
    }

    protected JWTToken<ID> cloneSafeUser(UserWithToken<ID> user){
        JWTToken<ID> clone = (JWTToken<ID>) super.clone();

        clone.setNotBefore(notBefore == null ? null : (Date) notBefore.clone());
        clone.setUser(user);
        return clone;
    }

    protected int hashCodeWithoutUser(){
        return Objects.hash(super.hashCode(), issuer, subject, notBefore, audience, secret, algorithm, type, userId);
    }
    //</editor-fold>
}
