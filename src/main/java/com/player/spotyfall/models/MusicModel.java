package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class MusicModel {
    /* Dados do banco de dados */
    private final String[] colunas_musics = new String[]{"id_music", "musicName", "musicImage"};
    private final String[] colunas_musicAuthor = new String[]{"id_music", "id_artist"};

    /* Metodos do banco de dados */
    public Database musics(){
        return new Database("musics").Columns(colunas_musics);
    }

    public Database musicAuthor(){
        return new Database("musics").Columns(colunas_musicAuthor);
    }
}
