package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Информация о токенах пользователя и его основные данные
 */
public class DtoUserToken<ID> extends DtoUser<ID> {

    /**
     * Токены пользователя
     */
    private Collection<DtoJWTToken<ID>> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(){
        tokens = new TreeSet<>();
    }

    public DtoUserToken() {
        super();
        tokens = new TreeSet<>();
    }

    public DtoUserToken(String username, String email, String passwordEncode) {
        super(username, email, passwordEncode);
        initialize();
    }

    public DtoUserToken(ID id, String username, String email, String passwordEncode) {
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
            throw new NullPointerException();
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
            throw new NullPointerException();
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
        return String.format("dto usertoken [%s]: %s", String.valueOf(getId()), getUsername());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoUserToken) {
            DtoUserToken object = (DtoUserToken) obj;
            return super.equals(object);
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode);
    }
    //</editor-fold>
}
