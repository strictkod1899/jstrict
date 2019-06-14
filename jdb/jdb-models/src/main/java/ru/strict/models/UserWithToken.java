package ru.strict.models;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Информация о токенах пользователя и его основные данные
 */
public class UserWithToken<ID> extends User<ID> {

    /**
     * Токены пользователя
     */
    private Collection<JWTToken<ID>> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(){
        tokens = new TreeSet<>();
    }

    public UserWithToken() {
        super();
        tokens = new TreeSet<>();
    }

    public UserWithToken(String username, String email, String passwordEncode) {
        super(username, email, passwordEncode);
        initialize();
    }

    public UserWithToken(ID id, String username, String email, String passwordEncode) {
        super(id, username, email, passwordEncode);
        initialize();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<JWTToken<ID>> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<JWTToken<ID>> tokens) {
        if(tokens == null) {
            throw new IllegalArgumentException("tokens is NULL");
        }

        for(JWTToken<ID> token : tokens){
            token.setUser(this);
        }

        this.tokens = tokens;
    }

    public void addToken(JWTToken<ID> token){
        addToken(token, true);
    }

    protected void addTokenSafe(JWTToken<ID> token){
        addToken(token, false);
    }

    private void addToken(JWTToken<ID> token, boolean isCircleMode){
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

    public void addTokens(Collection<JWTToken<ID>> tokens){
        if(tokens!=null) {
            for(JWTToken<ID> city : tokens){
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
        UserWithToken<ID> object = (UserWithToken<ID>) o;
        return Objects.equals(tokens, object.tokens);
    }

    @Override
    public int hashCode() {
        int tokensHashCode = 1;
        for(JWTToken<ID> token : tokens){
            tokensHashCode = 31 * tokensHashCode + (token == null ? 0 : token.hashCodeWithoutUser());
        }

        return Objects.hash(super.hashCode(), tokensHashCode);
    }

    @Override
    public UserWithToken<ID> clone(){
        UserWithToken<ID> clone = (UserWithToken<ID>) super.clone();

        clone.tokens = new TreeSet<>();
        for(JWTToken<ID> token : this.tokens){
            clone.addToken(token.cloneSafeUser(clone));
        }
        return clone;
    }
    //</editor-fold>
}
