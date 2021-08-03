package services;

import dao.*;
import dto.Role;
import dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private UsersRolesDAO usersRolesDAO = new UsersRolesDAOImpl();
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<User> findAll() throws SQLException {
        ResultSet resultSetAllUsers = userDAO.findAll();
        ResultSet resultSetRole;
        ArrayList<User> users = new ArrayList<>();

        String login;
        String email;
        String dob;
        User user;
        int id;
        while (resultSetAllUsers.next()) {
            ArrayList<Role> userRoles = new ArrayList<>();
            id = resultSetAllUsers.getInt(1);
            login = resultSetAllUsers.getString(2);
            email = resultSetAllUsers.getString(4);
            dob = resultSetAllUsers.getString(5);
            resultSetRole = roleDAO.getRoleByUserId(id);
            while (resultSetRole.next()) {
                int roleId = resultSetRole.getInt(1);
                String roleName = resultSetRole.getString(2);
                Role role = new Role(roleId, roleName);
                userRoles.add(role);
            }
            user = new User(login, userRoles, email, dob);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        ResultSet resultSetUser = userDAO.findByLogin(login);
        if (resultSetUser != null) {
            while (resultSetUser.next()) {
                int id = resultSetUser.getInt(1);
                String userLogin = resultSetUser.getString(2);
                String email = resultSetUser.getString(4);
                String dob = resultSetUser.getString(5);

                ResultSet resultSetRole = roleDAO.getRoleByUserId(id);
                if (resultSetRole != null) {
                    ArrayList<Role> userRoles = new ArrayList<>();

                    while (resultSetRole.next()) {
                        int idRole = resultSetRole.getInt(1);
                        String roleName = resultSetRole.getString(2);
                        Role role = new Role(idRole, roleName);
                        userRoles.add(role);
                    }
                    return new User(id, userLogin, userRoles, email, dob);
                }
            }
        }
        return null;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws SQLException {
        ResultSet userWithoutRole = userDAO.findByLoginAndPassword(login, password);
        User user = null;
        if (userWithoutRole != null) {
            while (userWithoutRole.next()) {
                int id = userWithoutRole.getInt(1);
                String findLogin = userWithoutRole.getString(2);
                ResultSet findRole = roleDAO.getRoleByUserId(id);
                if (findRole != null) {
                    ArrayList<Role> userRoles = new ArrayList<>();
                    while (findRole.next()) {
                        int idRole = findRole.getInt(1);
                        String roleName = findRole.getString(2);
                        Role role = new Role(idRole, roleName);
                        userRoles.add(role);

                    }
                    user = new User(findLogin, userRoles);
                }

            }
        }
        return user;
    }

    @Override
    public void updatePassword(String login, String newPassword) throws SQLException {
        userDAO.updatePasswordByLogin(login, newPassword);
    }

    @Override
    public void update(String login, String newLogin, String email, String dob, ArrayList<Role> roles) throws SQLException {
        ResultSet renewableUser = userDAO.findByLogin(login);
        if(renewableUser!=null){
            while (renewableUser.next()){
                usersRolesDAO.deleteRelation(renewableUser.getInt(1));
                userDAO.update(login, newLogin, email, dob);
                roles.forEach(role -> {
                    try {
                        usersRolesDAO.createRelation(renewableUser.getInt(1),role.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }



    }

    @Override
    public void delete(User user) throws SQLException {
        usersRolesDAO.deleteRelation(user.getId());
        userDAO.delete(user);
    }

    @Override
    public void create(User user) throws SQLException {

        userDAO.create(user);
        ResultSet newUser = userDAO.findByLogin(user.getLogin());
        if (newUser != null) {
            while (newUser.next()) {
                user.getRole().forEach(role -> {
                    try {
                        usersRolesDAO.createRelation(newUser.getInt(1), role.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

    }
}
