package com.task5.services;

import com.task5.dto.Role;
import com.task5.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public interface UserService {
    List<User> findAll() throws SQLException;

    User findByLogin(String login) throws SQLException;
    User findByLoginWithoutPass(String login) throws SQLException;

    User findByLoginAndPassword(String login, String password) throws SQLException;

    void updatePassword(String login, String newPassword) throws SQLException;

    void update(int user_id,String login, String newLogin,String newPassword, String email, String dob, ArrayList<Role> roles) throws SQLException;

    void delete(User user) throws SQLException;

    void create(User user) throws SQLException;
}
