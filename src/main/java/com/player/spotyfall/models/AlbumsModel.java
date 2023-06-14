package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class AlbumsModel {
    /* Dados do banco de dados */
    private final String[] colunas_albums = new String[]{"id_album", "albumName", "albumImage", "tipo"};

    /* Metodos do banco de dados */
    protected Database _albums(){
        return new Database("albums").Columns(colunas_albums);
    }

}
