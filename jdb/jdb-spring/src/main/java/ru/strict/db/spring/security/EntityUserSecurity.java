package ru.strict.db.spring.security;

import ru.strict.db.core.entities.EntityUser;

/**
 * Пользователь системы
 */
public class EntityUserSecurity<ID> extends EntityUser<ID> {

	//<editor-fold defaultState="collapsed" desc="constructors">
    public EntityUserSecurity() {
    	super();
	}

	public EntityUserSecurity(String username, String passwordEncode, String email) {
		super(username, passwordEncode, email);
	}

	public EntityUserSecurity(ID id, String username, String passwordEncode, String email) {
		super(id, username, passwordEncode, email);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("entity [%s]: %s", String.valueOf(getId()), getUsername());
	}

	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof EntityUserSecurity) {
			EntityUserSecurity object = (EntityUserSecurity) obj;
			return super.equals(object);
		}else
			return false;
	}
	//</editor-fold>
}
