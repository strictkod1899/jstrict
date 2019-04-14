package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Информация о токенах пользователя и его основные данные
 */
public class DtoUserWithToken<ID> extends DtoUser<ID> {

    /**
     * Токены пользователя
     */
    private Collection<DtoJWTToken<ID>> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(){
        tokens = new TreeSet<>();
    }

    public DtoUserWithToken() {
        super();
        tokens = new TreeSet<>();
    }

    public DtoUserWithToken(String username, String email, String passwordEncode) {
        super(username, email, passwordEncode);
        initialize();
    }

    public DtoUserWithToken(ID id, String username, String email, String passwordEncode) {
        super(id, username, email, passwordEncode);
        initialize();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<DtoJWTToken<ID>> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<DtoJWTToken<ID>> tokens) {
        if(tokens == null) {
            throw new IllegalArgumentException("tokens is NULL");
        }

        for(DtoJWTToken<ID> token : tokens){
            token.setUser(this);
        }

        this.tokens = tokens;
    }

    public void addToken(DtoJWTToken<ID> token){
        addToken(token, true);
    }

    protected void addTokenSafe(DtoJWTToken<ID> token){
        addToken(token, false);
    }

    private void addToken(DtoJWTToken<ID> token, boolean isCircleMode){
        if(token == null) {
            throw new IllegalArgumentException("token is NULL");
        }

        if(tokens != null){
            if(isCircleMode) {
                token.setUserSafe(this);
            }
            tokens.add(token);
        }
    }

    public void addTokens(Collection<DtoJWTToken<ID>> tokens){
        if(tokens!=null) {
            for(DtoJWTToken<ID> city : tokens){
                addToken(city);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("user [%s]: %s/%s", String.valueOf(getId()), getUsername(), getPasswordEncode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoUserWithToken<ID> that = (DtoUserWithToken<ID>) o;
        return Objects.equals(tokens, that.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tokens);
    }

    @Override
    public DtoUserWithToken<ID> clone(){
        DtoUser<ID> baseClone = super.clone();
        DtoUserWithToken<ID> clone = new DtoUserWithToken<>();

        clone.setId(getId());
        clone.setBlocked(baseClone.isBlocked());
        clone.setDeleted(baseClone.isDeleted());
        clone.setConfirmEmail(baseClone.isConfirmEmail());
        clone.setUsername(baseClone.getUsername());
        clone.setPasswordEncode(baseClone.getPasswordEncode());
        clone.setEmail(baseClone.getEmail());
        clone.setRoles(baseClone.getRoles());
        clone.setProfiles(baseClone.getProfiles());
        for(DtoJWTToken<ID> token : tokens){
            clone.addToken(token.clone());
        }
        return clone;
    }
    //</editor-fold>
}
