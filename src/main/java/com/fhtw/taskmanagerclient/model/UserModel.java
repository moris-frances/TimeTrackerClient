package com.fhtw.taskmanagerclient.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserModel {
    private String username;
    private String name;
    public ObservableList<EntryModel> entries = FXCollections.observableArrayList();
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
