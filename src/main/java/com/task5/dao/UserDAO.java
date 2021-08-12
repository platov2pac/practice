package com.task5.dao;

import com.task5.dto.Role;
import com.task5.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Mapper
public interface UserDAO {
    List<User> findAll() throws SQLException;
    User findByLogin(String login) throws SQLException;
    User findByLoginAndPassword(String login, String password) throws SQLException;
    void updatePassword(String login, String newPassword) throws SQLException;
    void update(int user_id, String login, String newLogin, String email, String dob) throws SQLException;
    void delete(User user) throws SQLException;
    void create(User user) throws SQLException;
    void createRole(int user_id, List<Role> roles);
    void deleteRole(User user);
}
