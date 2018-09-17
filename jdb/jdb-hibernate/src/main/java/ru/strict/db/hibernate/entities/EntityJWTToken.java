package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * JWT-токен
 */
@Entity
@Table(name = "token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityJWTToken<ID> extends EntityToken<ID> {

    /**
     * Издатель токена
     */
    @Column(name = "issuer", nullable = true)
    private String issuer;
    /**
     * Назначение токена
     */
    @Column(name = "subject", nullable = true)
    private String subject;
    /**
     * Дата, до которой токен не действителен
     */
    @Column(name = "notBefore", nullable = true)
    private Date notBefore;
    /**
     * Получатели токена
     */
    @Column(name = "audience", nullable = true)
    private String audience;
    /**
     * Секретный ключ для раскодирования токена
     */
    @Column(name = "secret", nullable = true)
    private String secret;
    /**
     * Алгоритм кодирования токена
     */
    @Column(name = "algorithm", nullable = true)
    private String algorithm;
    /**
     * Тип токена
     */
    @Column(name = "type", nullable = true)
    private String type;
    /**
     * Идентификатор пользователя, связанного с данным токеном
     */
    @Column(name = "userx_id", nullable = false)
    private ID userId;
    /**
     * Пользователь, связанного с данным токеном
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser<ID> user;
    /**
     * Идентификатор роли пользователя, связанного с данным токеном
     */
    @Column(name = "roleuser_id", nullable = false)
    private ID roleUserId;
    /**
     * Роль пользователя, связанного с данным токеном
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="roleuser_id", insertable = false, updatable = false)
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
            return super.equals(object) && issuer.equals(object.getIssuer())
                    && subject.equals(object.getSubject())
                    && notBefore.equals(object.getNotBefore())
                    && secret.equals(object.getSecret())
                    && algorithm.equals(object.getAlgorithm())
                    && type.equals(object.getType())
                    && userId.equals(object.getUserId())
                    && user.equals(object.getUser())
                    && roleUserId.equals(object.getRoleUserId())
                    && roleUser.equals(object.getRoleUser());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, issuer, subject, notBefore, secret,
                algorithm, type, userId, user, roleUserId, roleUser);
    }
    //</editor-fold>
}
