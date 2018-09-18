package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

import java.util.Date;

/**
 * JWT-токен
 */
public class DtoJWTToken<ID> extends DtoToken<ID> {

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
    private DtoUserToken<ID> user;
    /**
     * Идентификатор роли пользователя, связанного с данным токеном
     */
    private ID roleUserId;
    /**
     * Роль пользователя, связанного с данным токеном
     */
    private DtoRoleuser<ID> roleUser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID userId, ID roleUserId){
        if(userId == null){
            throw new NullPointerException("userId is NULL");
        } else if(roleUserId == null){
            throw new NullPointerException("roleUserId is NULL");
        }

        this.userId = userId;
        this.roleUserId = roleUserId;
        user = null;
        roleUser = null;
    }

    public DtoJWTToken() {
        super();
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        userId = null;
        user = null;
        roleUserId = null;
        roleUser = null;
    }

    public DtoJWTToken(String accessToken, String refreshToken, Date expireTimeAccess,
                       Date expireTimeRefresh, Date issuedAt, ID userId, ID roleUserId) {
        super(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        initialize(userId, roleUserId);
    }

    public DtoJWTToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess,
                       Date expireTimeRefresh, Date issuedAt, ID userId, ID roleUserId) {
        super(id, accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
        initialize(userId, roleUserId);
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
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.userId = userId;
    }

    public DtoUserToken<ID> getUser() {
        return user;
    }

    public void setUser(DtoUserToken<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(DtoUserToken<ID> user) {
        setUser(user, false);
    }

    private void setUser(DtoUserToken<ID> user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.addTokenSafe(this);
        }
        this.user = user;
    }

    public ID getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(ID roleUserId) {
        if(roleUserId == null) {
            throw new NullPointerException("roleUserId is NULL");
        }

        this.roleUserId = roleUserId;
    }

    public DtoRoleuser<ID> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(DtoRoleuser<ID> roleUser) {
        this.roleUser = roleUser;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity jwt-token [%s]: access - %s, refresh - %s", String.valueOf(getId()),
                getAccessToken(), getRefreshToken());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoJWTToken) {
            DtoJWTToken object = (DtoJWTToken) obj;
            return super.equals(object) && issuer.equals(object.getIssuer())
                    && subject.equals(object.getSubject())
                    && notBefore.equals(object.getNotBefore())
                    && secret.equals(object.getSecret())
                    && algorithm.equals(object.getAlgorithm())
                    && type.equals(object.getType())
                    && userId.equals(object.getUserId())
                    && roleUserId.equals(object.getRoleUserId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, issuer, subject, notBefore, secret,
                algorithm, type, userId, roleUserId);
    }
    //</editor-fold>
}
