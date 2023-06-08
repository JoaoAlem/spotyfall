package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class UserModel {
    /* Dados do banco de dados */
    private String[] colunas = new String[]{"id", "name", "surname", "username", "email", "phone", "userImage"};

    /* Metodos do banco de dados */
    public Database users(){
        return new Database("users").Columns(colunas);
    }
}
