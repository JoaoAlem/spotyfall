package com.player.spotyfall.modules.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.player.spotyfall.models.AlbumsModel;
import com.player.spotyfall.modules.Utils;
import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.DatabaseFault;

import java.sql.SQLException;
import java.util.Map;

public class AlbumMethods extends AlbumsModel {

    private final Database albumsTable;

    public AlbumMethods() {
        this.albumsTable = _albums();
    }

    public void SaveAlbum(Map<String, Object> data) throws DatabaseFault, SQLException {
        String id = null;
        try {
            if (data.containsKey("id_album")) {
                id = (String) data.get("id_album");
            }

            if (Utils.validateString(id) ) {
                albumsTable.Where("id_album", id).Put(data, id);
            } else {
                albumsTable.Put(data, id);
            }
        } finally {
            albumsTable.Sanitize();
        }
    }

    public String getAllAlbunsByArtistId(String id_artist) throws DatabaseFault, SQLException, JsonProcessingException {
        try {
           return albumsTable
                    .Where("id_artist", id_artist)
                    .WhereNull("deleteDate")
                    .Select();
        } finally {
            albumsTable.Sanitize();
        }
    }

    public String getAlbumById(String id_album) throws DatabaseFault, SQLException, JsonProcessingException {
        try {
            return albumsTable
                    .Where("id_album", id_album)
                    .WhereNull("deleteDate")
                    .SelectFirst();
        } finally {
            albumsTable.Sanitize();
        }
    }

    public void deleteAlbumById(String id) throws DatabaseFault, SQLException {
        try {
            albumsTable
                    .Where("id_album", id)
                    .Delete();
        } finally {
            albumsTable.Sanitize();
        }
    }
}
