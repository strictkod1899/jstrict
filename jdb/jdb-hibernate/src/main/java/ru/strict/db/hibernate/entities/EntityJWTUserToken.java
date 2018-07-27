package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * JWT-токен с информацией о пользователе базы данных
 */
@Entity
@Table(name = "token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityJWTUserToken extends EntityJWTToken {

    /**
     * Идентификатор пользователя, связанного с данным токеном
     */
    @Column(name = "userx_id")
    private UUID userId;
    /**
     * Пользователь, связанного с данным токеном
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser user;
    /**
     * Идентификатор роли пользователя, связанного с данным токеном
     */
    @Column(name = "roleuser_id")
    private UUID roleUserId;
    /**
     * Роль пользователя, связанного с данным токеном
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="roleuser_id", insertable = false, updatable = false)
    private EntityRoleuser roleUser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(UUID userId, UUID roleUserId){
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

    public EntityJWTUserToken() {
        super();
        userId = null;
        user = null;
        roleUserId = null;
        roleUser = null;
    }

    public EntityJWTUserToken(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt, UUID userId, UUID roleUserId) {
        super(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        initialize(userId, roleUserId);
    }

    public EntityJWTUserToken(UUID id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt, UUID userId, UUID roleUserId) {
        super(id, accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        initialize(userId, roleUserId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.userId = userId;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public UUID getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(UUID roleUserId) {
        if(roleUserId == null) {
            throw new NullPointerException("roleUserId is NULL");
        }

        this.roleUserId = roleUserId;
    }

    public EntityRoleuser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(EntityRoleuser roleUser) {
        this.roleUser = roleUser;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity jwt-token user [%s]: access - %s, refresh - %s", String.valueOf(getId()),
                getAccessToken(), getRefreshToken());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityJWTUserToken) {
            EntityJWTUserToken object = (EntityJWTUserToken) obj;
            return super.equals(object) && userId.equals(object.getUserId())
                    && user.equals(object.getUser())
                    && roleUserId.equals(object.getRoleUserId())
                    && roleUser.equals(object.getRoleUser());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, userId, user, roleUserId, roleUser);
    }
    //</editor-fold>
}
