package ru.strict.models;

import ru.strict.patterns.BaseModel;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class Role<ID> extends BaseModel<ID> {

    /**
     * Набор символов характеризующих роль
     */
    private String code;

    /**
     * Описание роли
     */
    private String description;

    /**
     * Пользователи свзяанные с ролью
     */
    private List<User<ID>> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(String code, String description) {
        if (code == null) {
            throw new IllegalArgumentException("code is NULL");
        }

        this.code = code;
        this.description = description;
        users = new ArrayList<>();
    }

    public Role() {
        super();
        code = null;
        description = null;
        users = new ArrayList<>();
    }

    public Role(String code, String description) {
        super();
        init(code, description);
    }

    public Role(ID id, String code, String description) {
        super(id);
        init(code, description);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User<ID>> getUsers() {
        return users;
    }

    public void setUsers(List<User<ID>> users) {
        if (users == null) {
            throw new IllegalArgumentException("users is NULL");
        }

        this.users = users;
    }

    public void addUser(User<ID> user) {
        if (user == null) {
            throw new IllegalArgumentException("user is NULL");
        }

        this.users.add(user);
    }

    public void addUsers(List<User<ID>> users) {
        if (users != null) {
            for (User<ID> user : users) {
                addUser(user);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("role [%s]: %s (%s)", String.valueOf(getId()), code, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role<ID> object = (Role<ID>) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(description, object.description) &&
                Objects.equals(users, object.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, users);
    }

    @Override
    public Role<ID> clone() {
        try {
            Role<ID> clone = (Role<ID>) super.clone();

            clone.users = new ArrayList<>();
            for (User<ID> user : this.users) {
                clone.addUser(user.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
