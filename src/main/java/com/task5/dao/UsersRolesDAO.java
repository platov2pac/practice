package com.task5.dao;

import java.sql.SQLException;

public interface UsersRolesDAO {
    void createRelation(int userId, int roleId) throws SQLException;

    void updateRelation(int userId, int roleId);

    void deleteRelation(int userId) throws SQLException;
}
