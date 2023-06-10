package com.player.spotyfall.models;

import com.player.spotyfall.modules.database.Database;

public class ArtistsModel {
    /* Dados do banco de dados */
    private final String[] colunas = new String[]{"id_artist", "tradeName",};

    /* Metodos do banco de dados */
    public Database artists(){
        return new Database("artists").Columns(colunas);
    }
}
