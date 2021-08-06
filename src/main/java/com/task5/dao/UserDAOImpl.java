package com.task5.dao;

import com.task5.dto.User;
import com.task5.storage.UserStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private UserStorage userStorage = UserStorage.getInstance();
    private Connection connection = userStorage.getConnection();

    @Override
    public List<User> findAll() throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
//        return preparedStatement.executeQuery();
        return null;
    }

    @Override
    public ResultSet findByLogin(String login) throws SQLException {
        PreparedStatement findQuery =
                connection.prepareStatement("select * from users u where u.login=?");
        findQuery.setString(1, login);
        return findQuery.executeQuery();
    }

    @Override
    public ResultSet findByLoginAndPassword(String login, String password) throws SQLException {
        PreparedStatement findQuery =
                connection.prepareStatement("select u.id, u.login from users u where u.login=? and u.pass=?");
        findQuery.setString(1, login);
        findQuery.setString(2, password);
        return findQuery.executeQuery();
    }

    @Override
    public void updatePasswordByLogin(String login, String newPassword) throws SQLException {
        PreparedStatement updatePasswordQuery = connection.prepareStatement("update users set pass=? where login=?");
        updatePasswordQuery.setString(1, newPassword);
        updatePasswordQuery.setString(2, login);
        updatePasswordQuery.executeQuery();
    }

    @Override
    public void update(String login, String newLogin, String email, String dob) throws SQLException {
        PreparedStatement updateUserQuery = connection.prepareStatement("update users\n" +
                "set login=?,\n" +
                "    email=?,\n" +
                "    dob=?\n" +
                "where login = ?");
        updateUserQuery.setString(1, newLogin);
        updateUserQuery.setString(2, email);
        updateUserQuery.setString(3, dob);
        updateUserQuery.setString(4, login);
        updateUserQuery.executeUpdate();
    }

    @Override
    public void delete(User user) throws SQLException {
       PreparedStatement deleteUserStatement = connection.prepareStatement(
               "delete from users where id = ?"
       );
       deleteUserStatement.setInt(1,user.getId());
       deleteUserStatement.execute();
    }

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement createUserStatement = connection.prepareStatement(
                "insert into users values (DEFAULT ,?,?,?,?)"
        );
        createUserStatement.setString(1,user.getLogin());
        createUserStatement.setString(2,user.getPassword());
        createUserStatement.setString(3,user.getEmail());
        createUserStatement.setString(4,user.getDob());
        createUserStatement.executeUpdate();
    }
}
