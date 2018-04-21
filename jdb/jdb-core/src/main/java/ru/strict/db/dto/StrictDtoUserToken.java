package ru.strict.db.dto;

public class StrictDtoUserToken<ID> extends StrictDtoUserBase<ID>{

    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserToken() {
        super();
        this.token = null;
    }

    public StrictDtoUserToken(String username, String token, StrictDtoRoleuser roleuser) {
        super(username, roleuser);
        this.token = token;
    }

    public StrictDtoUserToken(ID id, String username, String token, StrictDtoRoleuser roleuser) {
        super(id, username, roleuser);
        this.token = token;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s. Token: %s", super.toString(), token);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoUserToken) {
            StrictDtoUserToken entity = (StrictDtoUserToken) obj;
            return super.equals(entity) && token.equals(entity.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
