package ru.strict.db.spring.security;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.strict.utils.UtilHashCode;

/**
 * Специальный класс, который предоставляет данные пользователя для SpringSecurity.
 * Производит загрузку пользователей из базы данных
 */
public class StrictUserManager {

	private HashMap<String, DtoUserSecurity> users;

	public StrictUserManager(RepositoryUserSecurity rUser) {
		users = new HashMap();
		loadUsers(rUser);
	}

	public void loadUsers(RepositoryUserSecurity rUser){
		/*
		Загружаем пользователей из базы данных
		*/
		List<DtoUserSecurity> listUsers = rUser.readAll(null);
		for(DtoUserSecurity user : listUsers)
			users.put(user.getUsername(), user);
	}

	public DtoUserSecurity getUser(String username){
		try {
			if (!users.containsKey(username))
				throw new UsernameNotFoundException(username + " not found");

			return users.get(username);
		}catch(UsernameNotFoundException ex){
			return null;
		}
	}

	public HashMap<String, DtoUserSecurity> getUsers() {
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
			return users.size() == object.getUsers().size() && users.values().containsAll(object.getUsers().values());
		}else
			return false;
	}

	@Override
    public int hashCode(){
        return UtilHashCode.createHashCode(users);
    }
	//</editor-fold>
}
