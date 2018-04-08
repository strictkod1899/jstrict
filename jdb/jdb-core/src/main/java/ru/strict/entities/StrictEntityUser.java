package ru.strict.entities;

/**
 * Класс определяет entity пользователя системы
 */
public class StrictEntityUser extends StrictEntityBase<Long>{

    private String username;
    private String passwordmd5;
    private StrictEntityRoleuser roleuser;

    public StrictEntityUser() {
        super();
    }

    public StrictEntityUser(String username, String passwordmd5, StrictEntityRoleuser roleuser) {
        super();
        this.username = username;
        this.passwordmd5 = passwordmd5;
        this.roleuser = roleuser;
    }

    public StrictEntityUser(Long id, String username, String passwordmd5, StrictEntityRoleuser roleuser) {
        super(id);
        this.username = username;
        this.passwordmd5 = passwordmd5;
        this.roleuser = roleuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordmd5() {
        return passwordmd5;
    }

    public void setPasswordmd5(String passwordmd5) {
        this.passwordmd5 = passwordmd5;
    }

    public StrictEntityRoleuser getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(StrictEntityRoleuser roleuser) {
        this.roleuser = roleuser;
    }
}
