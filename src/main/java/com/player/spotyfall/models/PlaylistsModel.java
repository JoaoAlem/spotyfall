package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class PlaylistsModel {
    /* Dados do banco de dados */
    private final String[] colunas_playlists = new String[]{"id_playlist", "playlistName", "playlistImage", "playlistDesc"};
    private final String[] colunas_playlistsMusics = new String[]{"id_music", "id_playlist"};

    /* Metodos do banco de dados */
    public Database playlists(){
        return new Database("playlists").Columns(colunas_playlists);
    }

    public Database playlists_musics(){
        return new Database("playlistMusics").Columns(colunas_playlistsMusics);
    }
}
