package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class ArtistsModel {
    /* Dados do banco de dados */
    private final String[] colunas_artist = new String[]{"id_artist", "tradeName",};

    private final String[] colunas_musicAuthor = new String[]{"id_music", "id_artist"};

    /* Metodos do banco de dados */
    public Database artists(){
        return new Database("artists").Columns(colunas_artist);
    }

    public Database musicAuthor(){
        return new Database("musicAuthor").Columns(colunas_musicAuthor);
    }
}
