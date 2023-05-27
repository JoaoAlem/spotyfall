package com.player.spotyfall.modules.database;

import com.player.spotyfall.modules.Utils;
import org.apache.commons.text.StringSubstitutor;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    /* Conexão do banco de dados */
    private Connection conn;
    private String table;

    /* Atributos do banco de dados */
    private ArrayList<String[]> _whereConditions;
    private String[] _columns;
    private String _query;
    private ArrayList<String[]> _filters;

    /* Quando chamar o objeto, passar o nome da tabela */
    public Database(String tableName) {
        this.conn = _Connection.connect();
        this.table = tableName;

        this._whereConditions = new ArrayList<>();
        this._columns = new String[]{"*"};
        this._query = "";
        this._filters = new ArrayList<>();
    }

    private ResultSet execute() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(this._query);
        return ps.executeQuery();
    }

    public StringBuilder getWhere(){
        StringBuilder whereConditionsBuilder = new StringBuilder();
        for (String[] condition : this._whereConditions) {
            whereConditionsBuilder.append("(");
            whereConditionsBuilder.append(String.join(" ", condition));
            whereConditionsBuilder.append(") AND ");
        }

        if (this._whereConditions.size() > 0) {
            whereConditionsBuilder.insert(0, "WHERE ");
            whereConditionsBuilder.setLength(whereConditionsBuilder.length() - 5);
        }

        return whereConditionsBuilder;
    }

    public Database Columns(String[] colunas){
        this._columns = colunas;
        return this;
    }

    public Database Where(String condition) throws databaseFault {
        String[] item = new String[1];
        if(Utils.validateString(condition))
            item[0] = condition;
        else
            throw new databaseFault("Fill the fields");

        this._whereConditions.add(item);
        return this;
    }

    public Database Where(String column, String value) throws databaseFault {
        String[] item = new String[3];
        if(Utils.validateString(column) && Utils.validateString(value)){
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
        }else{
            throw new databaseFault("Fill the fields");
        }

        this._whereConditions.add(item);
        return this;
    }

    public Database Where(String column, String operator, String value) throws databaseFault {
        String[] item = new String[3];

        if(Utils.validateString(column) && Utils.validateString(operator) && Utils.validateString(value)){
            item[0] = column;
            item[1] = operator;
            item[2] = '"' + value + '"';
        }

        this._whereConditions.add(item);
        return this;
    }

    public ResultSet Select() throws SQLException {
        Map<String, String> _query = new HashMap<>();
        _query.put("columns", String.join(", ", this._columns));
        _query.put("where", this.getWhere().toString());
        _query.put("table", this.table);

        StringSubstitutor substitutor = new StringSubstitutor(_query);
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${where}");

        return execute();
    }
}
