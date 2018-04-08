package ru.strict.repositories;

import ru.strict.entities.StrictEntityRoleuser;
import ru.strict.entities.StrictEntityUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictRepositoryUsers extends StrictRepositoryBase<StrictEntityUser>{

    public StrictRepositoryUsers(Connection connection, String sqlSelect) {
        super(connection, sqlSelect);
    }

    public StrictRepositoryUsers(Connection connection) {
        super(connection, "SELECT * FROM users;");
    }

    @Override
    public StrictEntityUser initObject(ResultSet resultSet) {
        try {
            return new StrictEntityUser(resultSet.getLong("id"), resultSet.getString("username").trim(),
                    resultSet.getString("passwordmd5").trim(), new StrictRepositoryRoleusers(getConnection()).read(resultSet.getLong("roleuser")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(StrictEntityUser entityUser) {
        try {
            PreparedStatement pstm;
            if(entityUser.getId()==null) {
                pstm = getConnection().prepareStatement
                        ("INSERT INTO users (username, passwordmd5, roleuser) VALUES (?, ?, ?)");
                pstm.setString(1, entityUser.getUsername());
                pstm.setString(2, entityUser.getPasswordmd5());
                pstm.setLong(3, entityUser.getRoleuser().getId());
            }else{
                pstm = getConnection().prepareStatement
                        ("INSERT INTO users (id, username, passwordmd5, roleuser) VALUES (?, ?, ?, ?)");
                pstm.setLong(1, entityUser.getId());
                pstm.setString(2, entityUser.getUsername());
                pstm.setString(3, entityUser.getPasswordmd5());
                pstm.setLong(4, entityUser.getRoleuser().getId());
            }
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public StrictEntityUser read(Number id) {
        StrictEntityUser result;
        try {
            ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM users WHERE id = " + id + ";");
            StrictRepositoryBase roleuser = new StrictRepositoryRoleusers(getConnection());
            if(rs.next())
                result = new StrictEntityUser(rs.getLong("id"), rs.getString("username"),
                        rs.getString("passwordmd5"), (StrictEntityRoleuser) roleuser.read(rs.getLong("roleuser")));
            else
                result = null;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(StrictEntityUser strictEntityUser) {
        return false;
    }

    @Override
    public boolean update(StrictEntityUser strictEntityUser) {
        return false;
    }
}
