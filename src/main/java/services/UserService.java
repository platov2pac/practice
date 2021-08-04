package services;

import dto.Role;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<User> findAll() throws SQLException;

    User findByLogin(String login) throws SQLException;

    User findByLoginAndPassword(String login, String password) throws SQLException;

    void updatePassword(String login, String newPassword) throws SQLException;

    void update(String login, String newLogin, String email, String dob, ArrayList<Role> roles) throws SQLException;

    void delete(User user) throws SQLException;

    void create(User user) throws SQLException;
}
