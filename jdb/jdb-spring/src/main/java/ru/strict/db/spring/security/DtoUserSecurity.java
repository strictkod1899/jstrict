package ru.strict.db.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.utils.UtilHashCode;

import java.util.Collection;
import java.util.HashSet;

/**
 * Пользователь системы
 */
public class DtoUserSecurity<ID> extends DtoUser<ID> implements UserDetails{

	private static final long serialVersionUID = 8266525488057072269L;

	private Collection<GrantedAuthority> authorities;

	//<editor-fold defaultState="collapsed" desc="constructors">
    public DtoUserSecurity() {
    	super();
	}

	public DtoUserSecurity(String username, String passwordEncode) {
		super(username, passwordEncode);
	}

	public DtoUserSecurity(ID id, String username, String passwordEncode) {
		super(id, username, passwordEncode);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Get/Set">
	@Override
	public void addRoleuser(DtoRoleuser roleuser){
		this.authorities = new HashSet<>();
		if (roleuser.getCode() != null && !"".equals(roleuser.getCode())) {
			GrantedAuthority grandAuthority = new GrantedAuthority() {
				private static final long serialVersionUID = 3958183417696804555L;

				public String getAuthority() {
					return roleuser.getCode();
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
		return String.format("dto [%s]: %s. Token: %s", String.valueOf(getId()), getUsername());
	}

	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof DtoUserSecurity) {
			DtoUserSecurity object = (DtoUserSecurity) obj;
			return super.equals(object)
					&& (authorities.size() == object.getAuthorities().size() && authorities.containsAll(object.getAuthorities()));
		}else
			return false;
	}

	@Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, authorities);
    }
	//</editor-fold>
}
