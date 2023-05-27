package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.databaseFault;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuarios {
    /* Dados do usuario */
    private int id;
    private String name, surname, username, email, phone, password, userImage;

    /* Dados do banco de dados */
    private String[] colunas = new String[]{"name", "surname", "username"};

    /* Colocar dentro do construtor ou já criar com as colunas definidas
    public Usuarios(){
        this.colunas = new String[]{"name", "surname", "username"};
    }*/

    public Database users(){
        return new Database("users");
    }

    /* Method Overloading */
    public ResultSet getUsers() throws databaseFault, SQLException {
        return users()
                .Columns(colunas)
                .Where("deleteDate is null")
                .Select();
    }

    public ResultSet getUsers(String name) throws databaseFault, SQLException {
        return users()
                .Columns(colunas)
                .Where("name", "!=", "murilo")
                .Select();
    }

    public static void main(String[] args) throws databaseFault, SQLException {
        Usuarios users = new Usuarios();

        ResultSet rs = users.getUsers("joao");

        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
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
