package com.player.spotyfall.modules.database;

public class databaseFault extends Exception{
    public databaseFault(String mensagem) {
        super(mensagem);
    }
}
