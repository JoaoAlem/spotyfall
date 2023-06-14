package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class MusicModel {
    /* Dados do banco de dados */
    private final String[] colunas_musics = new String[]{"id_music", "musicName", "musicImage"};

    /* Metodos do banco de dados */
    public Database musics(){
        return new Database("musics").Columns(colunas_musics);
    }


}
