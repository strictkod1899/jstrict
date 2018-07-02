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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoJWTToken() {
        super();
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
    }

    public DtoJWTToken(String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super(accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
    }

    public DtoJWTToken(ID id, String accessToken, String refreshToken, Date expireTimeAccess, Date expireTimeRefresh, Date issuedAt) {
        super(id, accessToken, refreshToken, expireTimeAccess, expireTimeRefresh, issuedAt);
        issuer = null;
        subject = null;
        notBefore = null;
        secret = null;
        algorithm = null;
        type = null;
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
                    && type.equals(object.getType());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, issuer, subject, notBefore, secret, algorithm, type);
    }
    //</editor-fold>
}
