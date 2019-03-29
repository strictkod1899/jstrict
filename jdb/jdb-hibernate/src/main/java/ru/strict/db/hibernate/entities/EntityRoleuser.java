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
public class EntityRoleuser<ID> extends EntityBase<ID> {

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
    private Collection<EntityUser<ID>> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String code, String description){
        if(code == null) {
            throw new NullPointerException("code is NULL");
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

    public EntityRoleuser(ID id, String code, String description) {
        super(id);
        initialize(code, description);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(code == null) {
            throw new NullPointerException("code is NULL");
        }

        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<EntityUser<ID>> getUsers() {
        return users;
    }

    public void setUsers(Collection<EntityUser<ID>> users) {
        if(users == null) {
            throw new NullPointerException();
        }

        for(EntityUser<ID> user : users){
            user.addRoleSafe(this);
        }

        this.users = users;
    }

    public void addUser(EntityUser<ID> user){
        addUser(user, true);
    }

    protected void addUserSafe(EntityUser<ID> user){
        addUser(user, false);
    }

    private void addUser(EntityUser<ID> user, boolean isCircleMode){
        if(user == null) {
            throw new NullPointerException();
        }

        if(user != null){
            if(isCircleMode) {
                user.addRoleSafe(this);
            }
            users.add(user);
        }
    }

    public void addUsers(Collection<EntityUser<ID>> users){
        if(users!=null) {
            for(EntityUser<ID> user : users){
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
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityRoleuser) {
            EntityRoleuser object = (EntityRoleuser) obj;
            return super.equals(obj) && Objects.equals(code, object.getCode())
                    && Objects.equals(description, object.getDescription())
                    && Objects.equals(users, object.getUsers());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), code, description, users);
    }
    //</editor-fold>
}
