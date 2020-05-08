package ru.strict.db.spring.security;

import org.springframework.security.core.GrantedAuthority;
import ru.strict.models.Role;
import ru.strict.models.DetailsUser;
import ru.strict.validate.ValidateBaseValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Пользователь системы
 */
public class UserSecurity<ID> extends DetailsUser<ID> implements org.springframework.security.core.userdetails.DetailsUser {

	private static final long serialVersionUID = 8266525488057072269L;

	private Collection<GrantedAuthority> authorities;

	//<editor-fold defaultState="collapsed" desc="constructors">
    public UserSecurity() {
    	super();
		this.authorities = new HashSet<>();
	}

	public UserSecurity(DetailsUser<ID> user) {
		super(user.getId(), user.getUsername(), user.getEmail(), user.getPasswordEncode(), user.getSalt(), user.getSecret());
		this.authorities = new HashSet<>();
	}
	//</editor-fold>

	//<editor-fold defaultState="collapsed" desc="Get/Set">
	@Override
	public void addRole(Role<ID> role){
		if (!ValidateBaseValue.isEmptyOrNull(role.getCode())) {
			GrantedAuthority grandAuthority = new GrantedAuthority() {
				private static final long serialVersionUID = 3958183417696804555L;

				public String getAuthority() {
					return role.getCode();
				}

				@Override
				public int hashCode(){
					return Objects.hash(getAuthority());
				}
			};
			this.authorities.add(grandAuthority);
		}
		super.addRole(role);
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
		return !isBlocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !(isBlocked() || isDeleted());
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
