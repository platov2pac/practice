package com.task5.dto;

import java.util.Objects;

public class Role {
    private int id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
        if (name.equals("admin")) {
            this.id = 1;
        }
        if (name.equals("user")) {
            this.id = 2;
        }
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
