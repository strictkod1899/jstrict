package ru.strict.db.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Для того, чтобы связать наш сервис UserManager со Spring Security, нам нужно реализовать специальный интерфейс
 * фреймворка Spring Security который позволит выполнять аутентификацию пользователя
 * на основании данных полученых с UserManager.
 */
public class StrictUserDetailsService implements UserDetailsService {

	private StrictUserManager userManager;

	public StrictUserDetailsService(StrictUserManager userManager){
		this.userManager = userManager;
	}
	
	public UserDetails loadUserByUsername(String username){
		return userManager.getUser(username);
	}

}