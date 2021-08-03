package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User {
    private int id;
    private String login;
    private String password;
    private ArrayList<Role> role;
    private String email;
    private String dob;

    public User(String login, String password, ArrayList<Role> role, String email, String dob) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.dob = dob;
    }

    public User(int id, String login, ArrayList<Role> role, String email, String dob) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.email = email;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String login, String password, ArrayList<Role> role, String email, String dob) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.dob = dob;
    }

    public User(String login, ArrayList<Role> role, String email, String dob) {
        this.login = login;
        this.role = role;
        this.email = email;
        this.dob = dob;
    }

    public User(String login, String password, ArrayList<Role> role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, ArrayList<Role> role) {
        this.login = login;
        this.role = role;
    }

    public ArrayList<Role> getRole() {
        return role;
    }

    public String getRoleName() {
        return role.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public void setRole(ArrayList<Role> role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
