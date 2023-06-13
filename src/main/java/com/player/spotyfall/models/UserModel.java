package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class UserModel {
    /* Dados do banco de dados */
    private final String[] colunas_user = new String[]{"id_user", "name", "surname", "username", "email", "phone", "userImage"};
    private final String[] colunas_liked = new String[]{"id_music", "id_user"};
    private final String[] colunas_following = new String[]{"id_artist", "id_user"};

    /* Metodos do banco de dados */
    public Database _users(){
        return new Database("users").Columns(colunas_user);
    }

    public Database liked(){
        return new Database("liked").Columns(colunas_liked);
    }

    public Database following(){
        return new Database("artistsFollowed").Columns(colunas_following);
    }
}
