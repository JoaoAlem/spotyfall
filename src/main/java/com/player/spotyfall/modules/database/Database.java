package com.player.spotyfall.modules.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.spotyfall.modules.Utils;
import org.apache.commons.text.StringSubstitutor;

import java.sql.*;
import java.util.*;

public class Database {

    /* Conexão do banco de dados */
    private Connection conn;
    private String table;
    private String alias;

    /* Atributos do banco de dados */
    private ArrayList<String[]> _whereConditions;
    private String[] _columns;
    private String _query;
    private ArrayList<String[]> _filters;

    /* Quando chamar o objeto, passar o nome da tabela */
    public Database(String tableName) {
        this.conn = _Connection.connect();
        this.table = tableName;

        this._columns = new String[]{"*"};

        this._whereConditions = new ArrayList<>();
        this._query = "";
        this._filters = new ArrayList<>();
    }

    public Database(String tableName, String alias) {
        this.conn = _Connection.connect();
        this.table = tableName;
        this.alias = " AS " + alias;

        this._columns = new String[]{"*"};

        this._whereConditions = new ArrayList<>();
        this._query = "";
        this._filters = new ArrayList<>();
    }

    /*
    ===============================================
                    FUNÇÕES GERAIS
    ===============================================
     */

    /**
     * Função que vai executar a query do banco de dados, ainda somente selects
     *
     * @return ResultSet com oque foi encontrado do banco de dados
     * @throws SQLException um erro do SQL
     */
    private ResultSet execute() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(this._query);
        return ps.executeQuery();
    }

    /**
     * Função que vai executar a query do banco de dados, ainda somente selects
     *
     * @throws SQLException um erro do SQL
     */
    private void executeUpdate() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(this._query);

        if (this._values != null) {
            String[] valueArray = this._values.split(", ");
            for (int i = 0; i < valueArray.length; i++) {
                ps.setString(i + 1, valueArray[i]);
            }
        }

        ps.executeUpdate();
    }

    /**
     * Define as colunas desejadas para o objeto do banco de dados
     *
     * @param colunas nome das colunas em um Array
     * @return um objeto do banco de dados
     */
    public Database Columns(String[] colunas) {
        this._columns = colunas;
        return this;
    }

    /**
     * Método para limpar a query
     */
    private void Clear() {
        this._whereConditions = new ArrayList<>();
        this._query = "";
        this._filters = new ArrayList<>();
    }


    /**
     * método para fechar a conexão
     *
     * @throws SQLException
     */
    private void CloseConnection() throws SQLException {
        this.conn.close();
    }

    /**
     * Método que limpa e fecha a conexão
     */
    public void Sanitize() throws SQLException {
        Clear();
        CloseConnection();
    }

    /*
    ===============================================
                        WHERE
    ===============================================
     */

    /**
     * Função que irá "construir" as condições where, porem somente com o operador AND
     *
     * @return Instancia do objeto Database
     */
    private StringBuilder getWhere() {
        String operator = "";
        StringBuilder whereConditionsBuilder = new StringBuilder();
        for (String[] condition : this._whereConditions) {
            if (condition.length != 1) {
                // definindo o operador e atualizando o condition atual
                operator = condition[condition.length - 1];
                condition = Arrays.copyOf(condition, condition.length - 1);
            }

            whereConditionsBuilder.append(" (");
            whereConditionsBuilder.append(String.join(" ", condition));
            whereConditionsBuilder.append(") ");
            whereConditionsBuilder.append(operator);
        }

        if (this._whereConditions.size() > 0) {
            whereConditionsBuilder.insert(0, "WHERE ");
            whereConditionsBuilder.setLength(whereConditionsBuilder.length() - operator.length());
        }

        return whereConditionsBuilder;
    }

    /**
     * Método usado pela instancia do banco de dados para
     * definir as condições where e retornar a instancia
     *
     * @param item array de condições
     * @return instancia do objeto Database
     */
    private Database setWhere(String[] item) {
        this._whereConditions.add(item);
        return this;
    }


    /**
     * Define uma condição "Where column is null"
     *
     * @param column a coluna alvo
     * @return um objeto do banco de dados
     */
    public Database WhereNull(String column) {
        String[] item = new String[1];
        item[0] = column + " IS NULL";

        return setWhere(item);
    }

    /**
     * Define uma condição "Where column is not null"
     *
     * @param column a coluna alvo
     * @return um objeto do banco de dados
     */
    public Database WhereNotNull(String column) {
        String[] item = new String[1];
        item[0] = column + " IS NOT NULL";

        return setWhere(item);
    }

    /**
     * Define uma condição where
     *
     * @param condition a condição desejava
     * @return uma instancia do objeto database
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String condition) throws databaseFault {
        String[] item = new String[2];
        if (Utils.validateString(condition)) {
            item[0] = condition;
            item[1] = "AND";
        } else
            throw new databaseFault("Fill the fields");

        return setWhere(item);
    }

    /**
     * Define uma condição "where column = value"
     *
     * @param column coluna desejada
     * @param value  valor da coluna
     * @return instancia do objeto database
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String value) throws databaseFault {
        String[] item = new String[4];
        if (Utils.validateString(column) && Utils.validateString(value)) {
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
            item[3] = "AND";
        } else {
            throw new databaseFault("Fill the fields");
        }

        return setWhere(item);
    }

    /**
     * Define uma condição "where column operator value"
     *
     * @param column   coluna desejada
     * @param operator operador da operação
     * @param value    valor desejado
     * @return instancia do objeto databse
     * @throws databaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String operator, String value) throws databaseFault {
        String[] item = new String[4];

        if (Utils.validateString(column) && Utils.validateString(operator) && Utils.validateString(value)) {
            item[0] = column;
            item[1] = operator;
            item[2] = '"' + value + '"';
            item[3] = "AND";
        }

        return setWhere(item);
    }


    /**
     * Executa um where com a condição or
     *
     * @param column
     * @param value
     * @return
     * @throws databaseFault
     */
    public Database WhereOr(String column, String value) throws databaseFault {
        String[] item = new String[4];
        if (Utils.validateString(column) && Utils.validateString(value)) {
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
            item[3] = "OR";
        } else {
            throw new databaseFault("Fill the fields");
        }

        return setWhere(item);
    }
    /*
    ===============================================
                        SELECT
    ===============================================
     */

    /**
     * Método para realizar um select com todos os itens da tabela
     *
     * @return ResultSet com os resultados obtidos
     * @throws SQLException erro do sql
     */
    private Map<String, String> SelectMap() {
        Map<String, String> _query = new HashMap<>();
        _query.put("columns", String.join(", ", this._columns));
        _query.put("where", this.getWhere().toString());
        _query.put("table", this.table);
        _query.put("alias", this.alias);

        return _query;
    }

    /**
     * Função que executa um Select e retorna um ResultSet
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public String Select() throws SQLException, JsonProcessingException {
        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${alias} ${where}");

        return _Select();
    }

    /**
     * Função que executa um Select e retorna um ResultSet com somente 1 resultado
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public String SelectFirst() throws SQLException, JsonProcessingException {
        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${where} LIMIT 1");

        return _Select();
    }

    /**
     * Método que executa um select e retorna em json
     *
     * @return
     * @throws SQLException
     * @throws JsonProcessingException
     */
    private String _Select() throws SQLException, JsonProcessingException {
        ResultSet rs = execute();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<Map<String, Object>> resultList = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = rs.getObject(i);
                row.put(columnName, columnValue);
            }
            resultList.add(row);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        if (resultList.isEmpty()) {
            return "[]";
        } else {
            return objectMapper.writeValueAsString(resultList);
        }
    }

    /*
    ===============================================
                  INSERT E UPDATE
    ===============================================
     */
    private String _saveColumns = null;
    private String _values = null;

    public int _validateFields(Map<String, Object> data) {
        int columnCount = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();

            if (Utils.validateString(columnValue.toString())) {
                if (columnCount > 0) {
                    this._saveColumns += ", ";
                    this._values += ", ";
                }
                this._saveColumns += columnName;
                this._values += columnValue;
                columnCount++;
            }
        }
        return columnCount;
    }

    public void Put(Map<String, Object> data, String id) throws SQLException {
        Map<String, String> _queryHelper = new HashMap<>();
        _queryHelper.put("table", this.table);

        if (Utils.validateString(id)) {
            int columnCount = _validateFields(data);

            _queryHelper.put("where", this.getWhere().toString());
            _queryHelper.put("saveColumns", this._saveColumns);
            _queryHelper.put("values", this._values);

            this._query = "UPDATE ${table} SET ";

            // Remove the extra comma
            if (columnCount > 0) {
                this._query += "${saveColumns};";
            } else {
                this._query = "";
            }
        } else {
            this._query = "INSERT INTO ${table} (";

            // Initialize _saveColumns and _values
            this._saveColumns = "";
            this._values = "";

            int columnCount = _validateFields(data);

            // Append _saveColumns to the query
            this._query += this._saveColumns;

            if (columnCount > 0) {
                this._query += ") VALUES (";

                // Append _values with placeholders
                String[] placeholders = new String[columnCount];
                Arrays.fill(placeholders, "?");
                this._query += String.join(",", placeholders);
            }

            this._query += ");";
        }

        StringSubstitutor substitutor = new StringSubstitutor(_queryHelper);
        this._query = substitutor.replace(this._query);

        this.executeUpdate();
    }
}