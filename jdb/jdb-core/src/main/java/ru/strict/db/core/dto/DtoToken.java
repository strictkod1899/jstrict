package ru.strict.db.core.dto;

import java.util.Date;
import java.util.Objects;

/**
 * Токен
 */
public class DtoToken<ID> extends DtoBase<ID> {
    /**
     * Токен авторизации
     */
    private String accessToken;
    /**
     * Токен обновления
     */
    private String refreshToken;
    /**
     * Время окончания действия access-токена
     */
    private Date expireTimeAccess;
    /**
     * Время окончания действия refresh-токена
     */
    private Date expireTimeRefresh;
    /**
     * Время создания токена. Общее для access- и refresh-токена
     */
    private Date issuedAt;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt){
        if(accessToken == null) {
            throw new IllegalArgumentException("accessToken is NULL");
        } else if(refreshToken == null) {
            throw new IllegalArgumentException("refreshToken is NULL");
        }

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTimeAccess = expireTimeAccess;
        this.expireTimeRefresh = expireTimeRefresh;
        this.issuedAt = issuedAt;
    }

    public DtoToken() {
        super();
        accessToken = null;
        refreshToken = null;
        expireTimeAccess = null;
        expireTimeRefresh = null;
        issuedAt = null;
    }

    public DtoToken(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super();
        initialize(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    public DtoToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
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
        return String.format("jwt-token [%s]: access - %s, refresh - %s", String.valueOf(getId()), accessToken, refreshToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoToken<ID> dtoToken = (DtoToken<ID>) o;
        return Objects.equals(accessToken, dtoToken.accessToken) &&
                Objects.equals(refreshToken, dtoToken.refreshToken) &&
                Objects.equals(expireTimeAccess, dtoToken.expireTimeAccess) &&
                Objects.equals(expireTimeRefresh, dtoToken.expireTimeRefresh) &&
                Objects.equals(issuedAt, dtoToken.issuedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    @Override
    public DtoToken<ID> clone(){
        try {
            DtoToken<ID> clone = (DtoToken<ID>) super.clone();

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
