package ru.strict.models;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import ru.strict.patterns.BaseModel;
import ru.strict.validate.Validator;

/**
 * Базовая информация о пользователе (логин, роли, профиль)
 */
public class User<ID> extends BaseModel<ID> {

    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Адрес электронной почты
     */
    private String email;
    /**
     * Пользователь заблокирован
     */
    private boolean blocked;
    /**
     * Пользователь удален
     */
    private boolean deleted;
    /**
     * Адрес электронной почты подтвержден
     */
    private boolean confirmEmail;
    /**
     * Роли пользователя
     */
    private List<Role<ID>> roles;
    /**
     * Профили пользователя
     */
    private List<Profile<ID>> profiles;
    /**
     * Токены пользователя
     */
    private List<JWTToken<ID>> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(String username, String email) {
        Validator.isEmptyOrNull(username, "username").onThrow();
        Validator.isEmptyOrNull(username, "email").onThrow();

        this.username = username;
        this.email = email;
        blocked = false;
        deleted = false;
        confirmEmail = false;
        roles = new ArrayList<>();
        profiles = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public User() {
        super();
        username = null;
        email = null;
        blocked = false;
        deleted = false;
        confirmEmail = false;
        roles = new ArrayList<>();
        profiles = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public User(String username, String email) {
        super();
        init(username, email);
    }

    public User(ID id, String username, String email) {
        super(id);
        init(username, email);
    }

    public User(String username, String email, boolean blocked, boolean deleted, boolean confirmEmail) {
        super();
        init(username, email);
        this.blocked = blocked;
        this.deleted = deleted;
        this.confirmEmail = confirmEmail;
    }

    public User(ID id, String username, String email, boolean blocked, boolean deleted, boolean confirmEmail) {
        super(id);
        init(username, email);
        this.blocked = blocked;
        this.deleted = deleted;
        this.confirmEmail = confirmEmail;
    }

    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(boolean confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public List<Role<ID>> getRoles() {
        return roles;
    }

    public void setRoles(List<Role<ID>> roles) {
        if (roles == null) {
            throw new IllegalArgumentException("roles is NULL");
        }

        this.roles = roles;
    }

    public void addRole(Role<ID> role) {
        if (role == null) {
            throw new IllegalArgumentException("role is NULL");
        }

        this.roles.add(role);
    }

    public void addRoles(List<Role<ID>> roles) {
        if (roles != null) {
            for (Role<ID> user : roles) {
                addRole(user);
            }
        }
    }

    public List<Profile<ID>> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile<ID>> profiles) {
        if (profiles == null) {
            throw new IllegalArgumentException("profiles is NULL");
        }

        this.profiles = profiles;
    }

    public void addProfile(Profile<ID> profile) {
        if (profile == null) {
            throw new IllegalArgumentException("profile is NULL");
        }

        this.profiles.add(profile);
    }

    public void addProfiles(List<Profile<ID>> profiles) {
        if (profiles != null) {
            for (Profile<ID> profile : profiles) {
                addProfile(profile);
            }
        }
    }

    public List<JWTToken<ID>> getTokens() {
        return tokens;
    }

    public void setTokens(List<JWTToken<ID>> tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("tokens is NULL");
        }

        this.tokens = tokens;
    }

    public void addToken(JWTToken<ID> token) {
        if (token == null) {
            throw new IllegalArgumentException("token is NULL");
        }

        tokens.add(token);
    }

    public void addTokens(List<JWTToken<ID>> tokens) {
        if (tokens != null) {
            for (JWTToken<ID> city : tokens) {
                addToken(city);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("user [%s]: %s", String.valueOf(getId()), getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User<ID> object = (User<ID>) o;
        return blocked == object.blocked &&
                deleted == object.deleted &&
                confirmEmail == object.confirmEmail &&
                Objects.equals(username, object.username) &&
                Objects.equals(email, object.email) &&
                Objects.equals(roles, object.roles) &&
                Objects.equals(profiles, object.profiles) &&
                Objects.equals(tokens, object.tokens);
    }

    @Override
    public int hashCode() {
        int rolesHashCode = 1;
        for (Role<ID> role : roles) {
            rolesHashCode = 31 * rolesHashCode + (role == null ? 0 : role.hashCode());
        }
        int profilesHashCode = 1;
        for (Profile<ID> profile : profiles) {
            profilesHashCode = 31 * profilesHashCode + (profile == null ? 0 : profile.hashCode());
        }
        int tokensHashCode = 1;
        for (JWTToken<ID> token : tokens) {
            tokensHashCode = 31 * tokensHashCode + (token == null ? 0 : token.hashCode());
        }

        return Objects.hash(username, email, blocked, deleted, confirmEmail,
                rolesHashCode, profilesHashCode, tokensHashCode);
    }

    @Override
    public User<ID> clone() {
        try {
            User<ID> clone = (User<ID>) super.clone();

            clone.roles = new ArrayList<>();
            clone.profiles = new ArrayList<>();
            clone.tokens = new ArrayList<>();

            for (Role<ID> role : this.roles) {
                clone.addRole(role.clone());
            }
            for (Profile<ID> profile : this.profiles) {
                clone.addProfile(profile.clone());
            }
            for (JWTToken<ID> token : this.tokens) {
                clone.addToken(token.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
