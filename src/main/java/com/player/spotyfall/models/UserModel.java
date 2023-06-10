package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class UserModel {
    /* Dados do banco de dados */
    private final String[] colunas = new String[]{"id_user", "name", "surname", "username", "email", "phone", "userImage"};

    /* Metodos do banco de dados */
    public Database users(){
        return new Database("users").Columns(colunas);
    }
}
