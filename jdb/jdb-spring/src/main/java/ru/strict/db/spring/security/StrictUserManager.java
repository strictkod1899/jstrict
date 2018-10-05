package ru.strict.db.spring.security;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Специальный класс, который предоставляет данные пользователя для SpringSecurity.
 * Производит загрузку пользователей из базы данных
 */
public class StrictUserManager<ID> {

	private HashMap<String, DtoUserSecurity<ID>> users;

	public StrictUserManager(RepositoryUserSecurity<ID> repositoryUser) {
		users = new HashMap();
		loadUsers(repositoryUser);
	}

	public void loadUsers(RepositoryUserSecurity<ID> repositoryUser){
		/*
		Загружаем пользователей из базы данных
		*/
		List<DtoUserSecurity<ID>> users = repositoryUser.readAll(null);
		for(DtoUserSecurity<ID> user : users) {
			this.users.put(user.getUsername(), user);
		}
	}

	public DtoUserSecurity<ID> getUser(String username){
		try {
			if (!users.containsKey(username)) {
				throw new UsernameNotFoundException(username + " not found");
			}

			return users.get(username);
		}catch(UsernameNotFoundException ex){
			return null;
		}
	}

	public HashMap<String, DtoUserSecurity<ID>> getUsers() {
		return users;
	}

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("UserManager: count %s", users.size());
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
