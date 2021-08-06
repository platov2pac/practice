package com.task5.dao;

import com.task5.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> findAll() throws SQLException;

    ResultSet findByLogin(String login) throws SQLException;

    ResultSet findByLoginAndPassword(String login, String password) throws SQLException;

    void updatePasswordByLogin(String login, String newPassword) throws SQLException;

    void update(String login, String newLogin, String email, String dob) throws SQLException;

    void delete(User user) throws SQLException;

    void create(User user) throws SQLException;
}
