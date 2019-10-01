package ru.strict.db.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.strict.db.spring.repositories.RepositoryUserSecurity;

/**
 * Для того, чтобы связать наш сервис UserManager со Spring Security, нам нужно реализовать специальный интерфейс
 * фреймворка Spring Security который позволит выполнять аутентификацию пользователя
 * на основании данных полученых с UserManager.
 */
public class SpringUserDetailsService<ID> implements UserDetailsService {

	private RepositoryUserSecurity<ID> repositoryUser;

	public SpringUserDetailsService(RepositoryUserSecurity<ID> repositoryUser) {
		this.repositoryUser = repositoryUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username){
		return repositoryUser.readByName(username);
	}
}