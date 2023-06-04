package com.player.spotyfall.modules.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.player.spotyfall.models.UserModel;
import com.player.spotyfall.modules.Utils;
import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.databaseFault;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMethods extends UserModel
{
    private final Database userTable;
    public UserMethods(){
        userTable = users();
    }

    public String GetUser(String login, String password) throws databaseFault, SQLException, JsonProcessingException {
        try{
            return userTable
                    .Where("username", login)
                    .Where("password", password)
                    .Where("deleteDate is null")
                    .SelectFirst();
        } finally {
            userTable.Sanitize();
        }
    }

    public void SaveUser(Map<String, Object> data) throws databaseFault, SQLException {
        String id = null;
        try {
            if (data.containsKey("id")) {
                id = (String) data.get("id");
            }

            if(Utils.validateString(id)){
                userTable
                        .Where("id", id)
                        .Put(data, id);
            }else{
                userTable
                        .Put(data, id);
            }
        } finally {
            userTable.Sanitize();
        }
    }
}
