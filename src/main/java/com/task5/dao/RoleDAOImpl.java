package com.task5.dao;

import com.task5.storage.UserStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl implements RoleDAO {

    private UserStorage userStorage = UserStorage.getInstance();
    private Connection connection = userStorage.getConnection();

    @Override
    public ResultSet getRoleById(int id) {
        return null;
    }


    @Override
    public ResultSet getRoleByUserId(int userId) throws SQLException {
        PreparedStatement sqlQuery;
        ResultSet role;

        sqlQuery = connection.prepareStatement(
                "SELECT * FROM roles r\n" +
                        "LEFT JOIN users_roles ur ON r.id = ur.roles_id\n" +
                        "WHERE ur.user_id=?"
        );
        sqlQuery.setInt(1, userId);
        role = sqlQuery.executeQuery();
        return role;

    }
}
