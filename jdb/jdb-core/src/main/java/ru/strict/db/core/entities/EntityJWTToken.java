package ru.strict.db.core.entities;



import java.util.Date;
import java.util.Objects;

/**
 * JWT-токен
 */
public class EntityJWTToken<ID> extends EntityToken<ID> {

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
    private EntityUser<ID> user;
    /**
     * Идентификатор роли пользователя, связанного с данным токеном
     */
    private ID roleUserId;
    /**
     * Роль пользователя, связанного с данным токеном
     */
    private EntityRoleuser<ID> roleUser;

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

    public EntityJWTToken() {
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

    public EntityJWTToken(String accessToken, String refreshToken, Date expireTimeAccess,
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

    public EntityJWTToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess,
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

    public EntityUser<ID> getUser() {
        return user;
    }

    public void setUser(EntityUser<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(EntityUser<ID> user) {
        setUser(user, false);
    }

    private void setUser(EntityUser<ID> user, boolean isCircleMode) {
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

    public EntityRoleuser<ID> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(EntityRoleuser<ID> roleUser) {
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
        if(obj!=null && obj instanceof EntityJWTToken) {
            EntityJWTToken object = (EntityJWTToken) obj;
            return super.equals(obj) && Objects.equals(issuer, object.getIssuer())
                    && Objects.equals(subject, object.getSubject())
                    && Objects.equals(notBefore, object.getNotBefore())
                    && Objects.equals(audience, object.getAudience())
                    && Objects.equals(secret, object.getSecret())
                    && Objects.equals(algorithm, object.getAlgorithm())
                    && Objects.equals(type, object.getType())
                    && Objects.equals(userId, object.getUserId())
                    && Objects.equals(user, object.getUser())
                    && Objects.equals(roleUserId, object.getRoleUserId())
                    && Objects.equals(roleUser, object.getRoleUser());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getAccessToken(), getRefreshToken(), getExpireTimeAccess(),
                getExpireTimeRefresh(), getIssuedAt(), issuer, subject, notBefore, audience, secret,
                algorithm, type, userId, user, roleUserId, roleUser);
    }
    //</editor-fold>
}
