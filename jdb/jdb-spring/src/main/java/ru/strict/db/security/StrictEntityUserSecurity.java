package ru.strict.db.security;

import ru.strict.db.entities.StrictEntityUser;

/**
 * Пользователь системы
 */
public class StrictEntityUserSecurity<ID> extends StrictEntityUser<ID>{

	//<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUserSecurity() {
    	super();
	}

	public StrictEntityUserSecurity(String username, String passwordEncode, String token) {
		super(username, passwordEncode, token);
	}

	public StrictEntityUserSecurity(ID id, String username, String passwordEncode, String token) {
		super(id, username, passwordEncode, token);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("entity [%s]: %s. Token: %s", String.valueOf(getId()), getUsername(), getToken());
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof StrictEntityUserSecurity) {
			StrictEntityUserSecurity entity = (StrictEntityUserSecurity) obj;
			return super.equals(entity);
		}else
			return false;
	}
	//</editor-fold>
}
