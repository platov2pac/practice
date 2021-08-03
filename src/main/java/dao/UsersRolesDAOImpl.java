package dao;

import storage.UserStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRolesDAOImpl implements UsersRolesDAO {
    private UserStorage userStorage = UserStorage.getInstance();
    private Connection connection = userStorage.getConnection();

    @Override
    public void createRelation(int userId, int roleId) throws SQLException {
        PreparedStatement createStatement = connection.prepareStatement(
                "insert into users_roles values (?,?)");
        createStatement.setInt(1, userId);
        createStatement.setInt(2, roleId);
        createStatement.execute();
    }

    @Override
    public void updateRelation(int userId, int RoleId) {

    }

    @Override
    public void deleteRelation(int userId) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement(
                "delete from users_roles where user_id = ?"
        );
        deleteStatement.setInt(1,userId);
        deleteStatement.execute();
    }
}
