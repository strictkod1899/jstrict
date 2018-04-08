package ru.strict.repositories;

import ru.strict.entities.StrictEntityRoleuser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictRepositoryRoleusers extends StrictRepositoryBase<StrictEntityRoleuser>{

    public StrictRepositoryRoleusers(Connection connection, String sqlSelect) {
        super(connection, sqlSelect);
    }

    public StrictRepositoryRoleusers(Connection connection) {
        super(connection, "SELECT * FROM roleusers;");
    }

    @Override
    public StrictEntityRoleuser initObject(ResultSet resultSet) {
        try {
            return new StrictEntityRoleuser(resultSet.getLong("id")
                    , resultSet.getString("symbols").trim(), resultSet.getString("description").trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(StrictEntityRoleuser strictEntityRoleuser) {
        return false;
    }

    @Override
    public StrictEntityRoleuser read(Number id) {
        StrictEntityRoleuser result;
        try {
            ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM roleusers WHERE id = " + id + ";");
            if(rs.next())
                result = new StrictEntityRoleuser(rs.getLong("id"), rs.getString("symbols"), rs.getString("description"));
            else
                result = null;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(StrictEntityRoleuser strictEntityRoleuser) {
        return false;
    }

    @Override
    public boolean update(StrictEntityRoleuser strictEntityRoleuser) {
        return false;
    }
}
