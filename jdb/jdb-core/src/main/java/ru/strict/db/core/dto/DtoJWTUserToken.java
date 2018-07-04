package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

import java.util.Date;

/**
 * JWT-токен с информацией о пользователе базы данных
 */
public class DtoJWTUserToken<ID> extends DtoJWTToken<ID> {

    /**
     * Идентификатор пользователя, связанного с данным токеном
     */
    private ID userId;
    /**
     * Пользователь, связанного с данным токеном
     */
    private DtoUser user;
    /**
     * Идентификатор роли пользователя, связанного с данным токеном
     */
    private ID roleUserId;
    /**
     * Роль пользователя, связанного с данным токеном
     */
    private DtoRoleuser roleUser;

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

    public DtoJWTUserToken() {
        super();
        userId = null;
        user = null;
        roleUserId = null;
        roleUser = null;
    }

    public DtoJWTUserToken(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt, ID userId, ID roleUserId) {
        super(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        initialize(userId, roleUserId);
    }

    public DtoJWTUserToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt, ID userId, ID roleUserId) {
        super(id, accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        initialize(userId, roleUserId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.userId = userId;
    }

    public DtoUser getUser() {
        return user;
    }

    public void setUser(DtoUser user) {
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

    public DtoRoleuser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(DtoRoleuser roleUser) {
        this.roleUser = roleUser;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto jwt-token user [%s]: access - %s, refresh - %s", String.valueOf(getId()),
                getAccessToken(), getRefreshToken());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoJWTUserToken) {
            DtoJWTUserToken object = (DtoJWTUserToken) obj;
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
