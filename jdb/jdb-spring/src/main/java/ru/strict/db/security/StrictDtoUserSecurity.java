package ru.strict.db.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;

import java.util.Collection;
import java.util.HashSet;

/**
 * Пользователь системы
 */
public class StrictDtoUserSecurity<ID> extends StrictDtoUser<ID> implements UserDetails{

	private static final long serialVersionUID = 8266525488057072269L;

	private Collection<GrantedAuthority> authorities;

	//<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserSecurity() {
    	super();
	}

	public StrictDtoUserSecurity(String username, String passwordEncode, String token) {
		super(username, passwordEncode, token);
	}

	public StrictDtoUserSecurity(ID id, String username, String passwordEncode, String token) {
		super(id, username, passwordEncode, token);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Get/Set">
	@Override
	public void addRoleuser(StrictDtoRoleuser roleuser){
		this.authorities = new HashSet<>();
		if (roleuser.getSymbols() != null && !"".equals(roleuser.getSymbols())) {
			GrantedAuthority grandAuthority = new GrantedAuthority() {
				private static final long serialVersionUID = 3958183417696804555L;

				public String getAuthority() {
					return roleuser.getSymbols();
				}
			};
			this.authorities.add(grandAuthority);
		}
		super.addRoleuser(roleuser);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Spring Security override">
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return getPasswordEncode();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("dto [%s]: %s. Token: %s", String.valueOf(getId()), getUsername(), getToken());
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof StrictDtoUserSecurity) {
			StrictDtoUserSecurity entity = (StrictDtoUserSecurity) obj;
			return super.equals(entity)
					&& (authorities.size() == entity.getAuthorities().size() && authorities.containsAll(entity.getAuthorities()));
		}else
			return false;
	}
	//</editor-fold>
}
