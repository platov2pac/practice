package com.task5.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoleDAO {
    ResultSet getRoleById(int id);
    ResultSet getRoleByUserId(int userId) throws SQLException;
    //ResultSet getIdRoleByName(String roleName);
}
