package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
@Entity
@Table(name = "roleuser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityRoleuser extends EntityBase<Long> {

    /**
     * Набор символов характеризующих роль
     */
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * Описание роли
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * Пользователи свзяанные с ролью
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_on_role",
            joinColumns = @JoinColumn(name = "roleuser_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "userx_id", insertable = false, updatable = false))
    private Collection<EntityUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String code, String description){
        if(code == null) {
            throw new IllegalArgumentException("code is NULL");
        }

        this.code = code;
        this.description = description;
        users = new TreeSet<>();
    }

	public EntityRoleuser() {
        super();
        code = null;
        description = null;
        users = new TreeSet<>();
    }

    public EntityRoleuser(String code, String description) {
    	super();
        initialize(code, description);
    }

    public EntityRoleuser(Long id, String code, String description) {
        super(id);
        initialize(code, description);
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

    public Collection<EntityUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<EntityUser> users) {
        if(users == null) {
            throw new IllegalArgumentException("users is NULL");
        }

        for(EntityUser user : users){
            user.addRoleSafe(this);
        }

        this.users = users;
    }

    public void addUser(EntityUser user){
        addUser(user, true);
    }

    protected void addUserSafe(EntityUser user){
        addUser(user, false);
    }

    private void addUser(EntityUser user, boolean isCircleMode){
        if(user == null) {
            throw new IllegalArgumentException("user is NULL");
        }

        if(user != null){
            if(isCircleMode) {
                user.addRoleSafe(this);
            }
            users.add(user);
        }
    }

    public void addUsers(Collection<EntityUser> users){
        if(users!=null) {
            for(EntityUser user : users){
                addUser(user);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity roleuser [%s]: %s (%s)", String.valueOf(getId()), code, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityRoleuser object = (EntityRoleuser) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(description, object.description) &&
                Objects.equals(users, object.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, description, users);
    }

    @Override
    public EntityRoleuser clone(){
        try {
            EntityRoleuser clone = (EntityRoleuser) super.clone();

            clone.users = new TreeSet<>();
            for(EntityUser user : this.users){
                clone.addUser(user.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
