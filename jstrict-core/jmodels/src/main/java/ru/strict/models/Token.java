package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * Токен
 */
public class Token<ID> extends BaseModel<ID> {
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
    private void init(String accessToken,
            String refreshToken,
            Date expireTimeAccess,
            Date expireTimeRefresh,
            Date issuedAt) {
        if (accessToken == null) {
            throw new IllegalArgumentException("accessToken is NULL");
        } else if (refreshToken == null) {
            throw new IllegalArgumentException("refreshToken is NULL");
        }

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTimeAccess = expireTimeAccess;
        this.expireTimeRefresh = expireTimeRefresh;
        this.issuedAt = issuedAt;
    }

    public Token() {
        super();
        accessToken = null;
        refreshToken = null;
        expireTimeAccess = null;
        expireTimeRefresh = null;
        issuedAt = null;
    }

    public Token(String accessToken,
            String refreshToken,
            Date expireTimeAccess,
            Date expireTimeRefresh,
            Date issuedAt) {
        super();
        init(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    public Token(ID id,
            String accessToken,
            String refreshToken,
            Date expireTimeAccess,
            Date expireTimeRefresh,
            Date issuedAt) {
        super(id);
        init(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
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
    public String toString() {
        return String.format("jwt-token [%s]: access - %s, refresh - %s",
                String.valueOf(getId()),
                accessToken,
                refreshToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token<ID> token = (Token<ID>) o;
        return Objects.equals(accessToken, token.accessToken) &&
                Objects.equals(refreshToken, token.refreshToken) &&
                Objects.equals(expireTimeAccess, token.expireTimeAccess) &&
                Objects.equals(expireTimeRefresh, token.expireTimeRefresh) &&
                Objects.equals(issuedAt, token.issuedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }

    @Override
    public Token<ID> clone() {
        try {
            Token<ID> clone = (Token<ID>) super.clone();

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
