package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class UserModel {
    /* Dados do usuario */
    private int id;
    private String name, surname, username, email, phone, password, userImage;

    /* Dados do banco de dados */
    private String[] colunas = new String[]{"name", "surname", "username", "email", "phone", "userImage"};

    /* Metodos do banco de dados */
    public Database users(){
        return new Database("users").Columns(colunas);
    }

    /* Getters e setters */
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
