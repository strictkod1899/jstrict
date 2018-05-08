package ru.strict.db.security;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Специальный класс, который предоставляет данные пользователя для SpringSecurity.
 * Производит загрузку пользователей из базы данных
 */
@Service
public class StrictUserManager {

	private HashMap<String, StrictDtoUserSecurity> users;

	@Autowired
	public StrictUserManager(StrictRepositoryUserSecurity rUser) {
		users = new HashMap();
		loadUsers(rUser);
	}

	public void loadUsers(StrictRepositoryUserSecurity rUser){
		/*
		Загружаем пользователей из базы данных
		*/
		List<StrictDtoUserSecurity> listUsers = rUser.readAll(null);
		for(StrictDtoUserSecurity user : listUsers)
			users.put(user.getUsername(), user);
	}

	public StrictDtoUserSecurity getUser(String username){
		try {
			if (!users.containsKey(username))
				throw new UsernameNotFoundException(username + " not found");

			return users.get(username);
		}catch(UsernameNotFoundException ex){
			return null;
		}
	}

	public HashMap<String, StrictDtoUserSecurity> getUsers() {
		return users;
	}

	//<editor-fold defaultState="collapsed" desc="Base override">
	@Override
	public String toString(){
		return String.format("UserManager: count %s", users.size());
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof StrictUserManager) {
			StrictUserManager object = (StrictUserManager) obj;
			return super.equals(object)
					&& (users.size() == object.getUsers().size() && users.values().containsAll(object.getUsers().values()));
		}else
			return false;
	}
	//</editor-fold>
}
