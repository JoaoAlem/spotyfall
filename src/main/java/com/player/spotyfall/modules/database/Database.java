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

    /*
    ===============================================
                    FUNÇÕES GERAIS
    ===============================================
     */

    /** Função que vai executar a query do banco de dados, ainda somente selects
     *
     * @return ResultSet com oque foi encontrado do banco de dados
     * @throws SQLException um erro do SQL
     */
    private ResultSet execute() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(this._query);
        return ps.executeQuery();
    }

    /** Define as colunas desejadas para o objeto do banco de dados
     *
     * @param colunas nome das colunas em um Array
     * @return um objeto do banco de dados
     */
    public Database Columns(String[] colunas){
        this._columns = colunas;
        return this;
    }

    /*
    ===============================================
                        WHERE
    ===============================================
     */

    /** Função que irá "construir" as condições where, porem somente com o operador AND
     *
     * @return Instancia do objeto Database
     */
    private StringBuilder getWhere(){
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

    /** Método usado pela instancia do banco de dados para
     *  definir as condições where e retornar a instancia
     * @param item array de condições
     * @return instancia do objeto Database
     */
    private Database setWhere(String[] item){
        this._whereConditions.add(item);
        return this;
    }


    /** Define uma condição "Where column is null"
     *
     * @param column a coluna alvo
     * @return um objeto do banco de dados
     */
    public Database WhereNull(String column){
        String[] item = new String[1];
        item[0] = column + " IS NULL";

        return setWhere(item);
    }

    /** Define uma condição "Where column is not null"
     *
     * @param column a coluna alvo
     * @return um objeto do banco de dados
     */
    public Database WhereNotNull(String column){
        String[] item = new String[1];
        item[0] = column + " IS NOT NULL";

        return setWhere(item);
    }

    /** Define uma condição where
     *
     * @param condition a condição desejava
     * @return uma instancia do objeto database
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String condition) throws databaseFault {
        String[] item = new String[1];
        if(Utils.validateString(condition))
            item[0] = condition;
        else
            throw new databaseFault("Fill the fields");

        return setWhere(item);
    }

    /** Define uma condição "where column = value"
     *
     * @param column coluna desejada
     * @param value valor da coluna
     * @return instancia do objeto database
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String value) throws databaseFault {
        String[] item = new String[3];
        if(Utils.validateString(column) && Utils.validateString(value)){
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
        }else{
            throw new databaseFault("Fill the fields");
        }

        return setWhere(item);
    }

    /** Define uma condição "where column operator value"
     *
     * @param column coluna desejada
     * @param operator operador da operação
     * @param value valor desejado
     * @return instancia do objeto databse
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String operator, String value) throws databaseFault {
        String[] item = new String[3];

        if(Utils.validateString(column) && Utils.validateString(operator) && Utils.validateString(value)){
            item[0] = column;
            item[1] = operator;
            item[2] = '"' + value + '"';
        }

        return setWhere(item);
    }

    /*
    ===============================================
                        SELECT
    ===============================================
     */

    /** Método para realizar um select com todos os itens da tabela
     *
     * @return ResultSet com os resultados obtidos
     * @throws SQLException erro do sql
     */
    private Map SelectMap(){
        Map<String, String> _query = new HashMap<>();
        _query.put("columns", String.join(", ", this._columns));
        _query.put("where", this.getWhere().toString());
        _query.put("table", this.table);

        return _query;
    }

    /** Função que executa um Select e retorna um ResultSet
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public ResultSet Select() throws SQLException {

        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${where}");

        return execute();
    }

    /** Função que executa um Select e retorna um ResultSet com somente 1 resultado
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public ResultSet SelectFirst() throws SQLException {
        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${where} LIMIT 1");

        return execute();
    }

    /*
    ===============================================
                  INSERT E UPDATE
    ===============================================
     */

    public void Put(Array data, Database table){

    }
}