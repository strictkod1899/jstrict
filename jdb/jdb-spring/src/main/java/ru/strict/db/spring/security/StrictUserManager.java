package ru.strict.db.spring.security;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Специальный класс, который предоставляет данные пользователя для SpringSecurity.
 * Производит загрузку пользователей из базы данных
 */
public class StrictUserManager<ID> {

	private HashMap<String, UserSecurity<ID>> users;

	public StrictUserManager(RepositoryUserSecurity<ID> repositoryUser) {
		users = new HashMap();
		loadUsers(repositoryUser);
	}

	public void loadUsers(RepositoryUserSecurity<ID> repositoryUser){
		/*
		Загружаем пользователей из базы данных
		*/
		List<UserSecurity<ID>> users = repositoryUser.readAll(null);
		for(UserSecurity<ID> user : users) {
			this.users.put(user.getUsername(), user);
		}
	}

	public UserSecurity<ID> getUser(String username){
		try {
			if (!users.containsKey(username)) {
				throw new UsernameNotFoundException(username + " not found");
			}

			return users.get(username);
		}catch(UsernameNotFoundException ex){
			return null;
		}
	}

	public HashMap<String, UserSecurity<ID>> getUsers() {
		return users;
	}

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("UserManager. Count users: %s", users.size());
	}

	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof StrictUserManager) {
			StrictUserManager object = (StrictUserManager) obj;
			return Objects.equals(users, object.getUsers());
		}else {
			return false;
		}
	}

	@Override
    public int hashCode(){
        return Objects.hash(users);
    }
	//</editor-fold>
}
