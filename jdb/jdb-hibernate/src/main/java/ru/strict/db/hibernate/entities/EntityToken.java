package ru.strict.db.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

/**
 * Токен
 */
@MappedSuperclass
public class EntityToken extends EntityBase<Long> {
    /**
     * Токен авторизации
     */
    @Column(name = "access_token", nullable = false)
    private String accessToken;
    /**
     * Токен обновления
     */
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
    /**
     * Время окончания действия access-токена
     */
    @Column(name = "expire_time_access", nullable = false)
    private Date expireTimeAccess;
    /**
     * Время окончания действия refresh-токена
     */
    @Column(name = "expire_time_refresh", nullable = false)
    private Date expireTimeRefresh;
    /**
     * Время создания токена. Общее для access- и refresh-токена
     */
    @Column(name = "issued_at", nullable = false)
    private Date issuedAt;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt){
        if(accessToken == null) {
            throw new IllegalArgumentException("accessToken is NULL");
        } else if(refreshToken == null) {
            throw new IllegalArgumentException("refreshToken is NULL");
        } else if(expireTimeAccess == null) {
            throw new IllegalArgumentException("expireTimeAccess is NULL");
        } else if(expireTimeRefresh == null) {
            throw new IllegalArgumentException("expireTimeRefresh is NULL");
        } else if(issuedAt == null) {
            throw new IllegalArgumentException("issuedAt is NULL");
        }

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTimeAccess = expireTimeAccess;
        this.expireTimeRefresh = expireTimeRefresh;
        this.issuedAt = issuedAt;
    }

    public EntityToken() {
        super();
        accessToken = null;
        refreshToken = null;
        expireTimeAccess = null;
        expireTimeRefresh = null;
        issuedAt = null;
    }

    public EntityToken(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super();
        initialize(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    public EntityToken(Long id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super(id);
        initialize(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpireTimeAccess() {
        return expireTimeAccess;
    }

    public void setExpireTimeAccess(Date expireTimeAccess) {
        this.expireTimeAccess = expireTimeAccess;
    }

    public Date getExpireTimeRefresh() {
        return expireTimeRefresh;
    }

    public void setExpireTimeRefresh(Date expireTimeRefresh) {
        this.expireTimeRefresh = expireTimeRefresh;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity jwt-token [%s]: access - %s, refresh - %s", String.valueOf(getId()), accessToken, refreshToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityToken object = (EntityToken) o;
        return Objects.equals(accessToken, object.accessToken) &&
                Objects.equals(refreshToken, object.refreshToken) &&
                Objects.equals(expireTimeAccess, object.expireTimeAccess) &&
                Objects.equals(expireTimeRefresh, object.expireTimeRefresh) &&
                Objects.equals(issuedAt, object.issuedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    @Override
    public EntityToken clone(){
        try {
            EntityToken clone = (EntityToken) super.clone();

            clone.setExpireTimeAccess(expireTimeAccess == null ? null : (Date) expireTimeAccess.clone());
            clone.setExpireTimeRefresh(expireTimeRefresh == null ? null : (Date) expireTimeRefresh.clone());
            clone.setIssuedAt(issuedAt == null ? null : (Date) issuedAt.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
