package com.player.spotyfall.modules.database;

public class DatabaseFault extends Exception{
    public DatabaseFault(String mensagem) {
        super(mensagem);
    }
}
