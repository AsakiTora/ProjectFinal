package com.app.projectfinal.Model;

public class GroupUser {
    int id;
    String name;

    public GroupUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupUser() {
    }

    public GroupUser(String name) {
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
}
