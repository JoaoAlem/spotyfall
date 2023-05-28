package com.player.spotyfall.modules.custom;

import com.player.spotyfall.models.UserModel;
import com.player.spotyfall.modules.database.Database;
import com.player.spotyfall.modules.database.databaseFault;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMethods extends UserModel
{
    private Database userTable = new UserModel().users();

    public ResultSet GetUser(String login, String password) throws databaseFault, SQLException
    {
        return userTable
                .Where("username", login)
                .Where("password", password)
                .Where("deleteDate is null")
                .SelectFirst();
    }
}
