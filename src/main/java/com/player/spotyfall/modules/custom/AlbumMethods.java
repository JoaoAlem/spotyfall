package com.player.spotyfall.modules.custom;

import com.player.spotyfall.models.AlbumsModel;
import com.player.spotyfall.modules.Utils;
import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.databaseFault;

import java.sql.SQLException;
import java.util.Map;

public class AlbumMethods extends AlbumsModel {

    private final Database albumsTable;

    public AlbumMethods() {
        this.albumsTable = _albums();
    }

    public void SaveAlbum(Map<String, Object> data) throws databaseFault, SQLException {
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
}
