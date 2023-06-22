package com.player.spotyfall.modules.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.player.spotyfall.models.ArtistsModel;
import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.DatabaseFault;

import java.sql.SQLException;
import java.util.Map;

public class ArtistsMethods extends ArtistsModel {

    private final Database artistsTable;

    public ArtistsMethods() {
        this.artistsTable = _artists();
    }

    public void SaveArtist(Map<String, Object> data) throws DatabaseFault, SQLException {
        try {
            artistsTable
                    .Put(data, "");
        } finally {
            artistsTable.Sanitize();
        }
    }

    public String getArtistByUserId(String userId)throws DatabaseFault, SQLException, JsonProcessingException{
        try{
            return artistsTable
                    .Where("id_user", userId)
                    .SelectFirst();
        } finally {
            artistsTable.Sanitize();
        }
    }
}