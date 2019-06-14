package ru.strict.db.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.validates.ValidateBaseValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Пользователь системы
 */
public class UserSecurity<ID> extends User<ID> implements UserDetails{

	private static final long serialVersionUID = 8266525488057072269L;

	private Collection<GrantedAuthority> authorities;

	//<editor-fold defaultState="collapsed" desc="constructors">
    public UserSecurity() {
    	super();
	}

	public UserSecurity(String username, String email, String passwordEncode) {
		super(username, email, passwordEncode);
	}

	public UserSecurity(ID id, String username, String email, String passwordEncode) {
		super(id, username, email, passwordEncode);
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Get/Set">
	@Override
	public void addRole(Roleuser<ID> roleuser){
		this.authorities = new HashSet<>();
		if (roleuser.getCode() != null && !ValidateBaseValue.isEmptyOrNull(roleuser.getCode())) {
			GrantedAuthority grandAuthority = new GrantedAuthority() {
				private static final long serialVersionUID = 3958183417696804555L;

				public String getAuthority() {
					return roleuser.getCode();
				}

				@Override
				public int hashCode(){
					return Objects.hash(roleuser);
				}
			};
			this.authorities.add(grandAuthority);
		}
		super.addRole(roleuser);
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		UserSecurity<ID> object = (UserSecurity<ID>) o;
		return Objects.equals(authorities, object.authorities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), authorities);
	}
	//</editor-fold>
}
