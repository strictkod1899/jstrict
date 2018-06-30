package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Информация о токенах пользователя и его основные данные
 */
public class DtoUserToken<ID> extends DtoUser<ID> {

    /**
     * Токены пользователя
     */
    private Collection<DtoJWTUserToken> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoUserToken() {
        super();
        tokens = new LinkedList<>();
    }

    public DtoUserToken(String username, String passwordEncode) {
        super(username, passwordEncode);
        tokens = new LinkedList<>();
    }

    public DtoUserToken(ID id, String username, String passwordEncode) {
        super(id, username, passwordEncode);
        tokens = new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Добавить токен
     */
    public void addToken(DtoJWTUserToken token){
        tokens.add(token);
    }

    public Collection<DtoJWTUserToken> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<DtoJWTUserToken> tokens) {
        this.tokens = tokens;
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
            return super.equals(object) && (tokens.size() == object.getTokens().size() && tokens.containsAll(object.getTokens()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, tokens);
    }
    //</editor-fold>
}
