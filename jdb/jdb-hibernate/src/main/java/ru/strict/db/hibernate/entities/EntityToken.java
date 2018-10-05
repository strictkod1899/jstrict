package ru.strict.db.hibernate.entities;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

/**
 * Токен
 */
@MappedSuperclass
public class EntityToken<ID> extends EntityBase<ID> {
    /**
     * Токен авторизации
     */
    @Column(name = "accessToken", nullable = false)
    private String accessToken;
    /**
     * Токен обновления
     */
    @Column(name = "refreshToken", nullable = false)
    private String refreshToken;
    /**
     * Время окончания действия access-токена
     */
    @Column(name = "expireTimeAccess", nullable = false)
    private Date expireTimeAccess;
    /**
     * Время окончания действия refresh-токена
     */
    @Column(name = "expireTimeRefresh", nullable = false)
    private Date expireTimeRefresh;
    /**
     * Время создания токена. Общее для access- и refresh-токена
     */
    @Column(name = "issuedAt", nullable = false)
    private Date issuedAt;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt){
        if(accessToken == null) {
            throw new NullPointerException("accessToken is NULL");
        } else if(refreshToken == null) {
            throw new NullPointerException("refreshToken is NULL");
        } else if(expireTimeAccess == null) {
            throw new NullPointerException("expireTimeAccess is NULL");
        } else if(expireTimeRefresh == null) {
            throw new NullPointerException("expireTimeRefresh is NULL");
        } else if(issuedAt == null) {
            throw new NullPointerException("issuedAt is NULL");
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

    public EntityToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super(id);
        initialize(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        if(accessToken == null) {
            throw new NullPointerException("accessToken is NULL");
        }

        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        if(refreshToken == null) {
            throw new NullPointerException("refreshToken is NULL");
        }

        this.refreshToken = refreshToken;
    }

    public Date getExpireTimeAccess() {
        return expireTimeAccess;
    }

    public void setExpireTimeAccess(Date expireTimeAccess) {
        if(expireTimeAccess == null) {
            throw new NullPointerException("expireTimeAccess is NULL");
        }

        this.expireTimeAccess = expireTimeAccess;
    }

    public Date getExpireTimeRefresh() {
        return expireTimeRefresh;
    }

    public void setExpireTimeRefresh(Date expireTimeRefresh) {
        if(expireTimeRefresh == null) {
            throw new NullPointerException("expireTimeRefresh is NULL");
        }

        this.expireTimeRefresh = expireTimeRefresh;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        if(issuedAt == null) {
            throw new NullPointerException("issuedAt is NULL");
        }

        this.issuedAt = issuedAt;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity jwt-token [%s]: access - %s, refresh - %s", String.valueOf(getId()), accessToken, refreshToken);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityToken) {
            EntityToken object = (EntityToken) obj;
            return super.equals(object) && accessToken.equals(object.getAccessToken())
                    && refreshToken.equals(object.getRefreshToken())
                    && expireTimeAccess.equals(object.getExpireTimeAccess())
                    && expireTimeRefresh.equals(object.getExpireTimeRefresh())
                    && issuedAt.equals(object.getIssuedAt());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
    }
    //</editor-fold>
}
