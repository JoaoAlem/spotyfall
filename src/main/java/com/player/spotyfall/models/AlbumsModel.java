package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class AlbumsModel {
    /* Dados do banco de dados */
    private final String[] colunas_albums = new String[]{"id_album", "albumName", "albumImage", "tipo"};
    private final String[] colunas_albumsMusics = new String[]{"id_album", "id_music"};

    /* Metodos do banco de dados */
    public Database albums(){
        return new Database("users").Columns(colunas_albums);
    }

    public Database albums_musics(){
        return new Database("users").Columns(colunas_albumsMusics);
    }
}
