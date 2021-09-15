package com.task5.services;

import com.task5.dao.*;
import com.task5.dto.Role;
import com.task5.dto.User;
import com.task5.services.errorsProcessing.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAOBatis;


    @Override
    public List<User> findAll() throws SQLException {
        return userDAOBatis.findAll();
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        User user = userDAOBatis.findByLogin(login);
        if (user == null) {
            return null;
//            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public User findByLoginWithoutPass(String login) throws SQLException {
        User user = userDAOBatis.findByLoginWithoutPass(login);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws SQLException {
        User user = userDAOBatis.findByLoginAndPassword(login, password);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public void updatePassword(String login, String newPassword) throws SQLException {
        userDAOBatis.updatePassword(login, newPassword);
    }

    @Override
    public void update(int user_id, String login, String newLogin,String newPassword,  String email, String dob, ArrayList<Role> roles) throws SQLException {
        userDAOBatis.deleteRole(findByLogin(login));
        userDAOBatis.update(user_id, login, newLogin,newPassword, email, dob);
        userDAOBatis.createRole(user_id, roles);

    }

    @Override
    public void delete(User user) throws SQLException {
        userDAOBatis.deleteRole(user);
        userDAOBatis.delete(user);
    }

    @Override
    public void create(User user) throws SQLException {
        userDAOBatis.create(user);
        userDAOBatis.createRole(userDAOBatis.findByLogin(user.getLogin()).getId(), user.getRoles());
    }
}
