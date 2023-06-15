package com.player.spotyfall.modules.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.player.spotyfall.modules.Utils;
import org.apache.commons.text.StringSubstitutor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class Database {

    /* Conexão do banco de dados */
    private Connection conn;
    private String table;
    private String alias;

    /* Atributos do banco de dados */
    private ArrayList<String[]> _whereConditions;
    private ArrayList<String[]> _joinConditions;
    private String[] _columns;
    private String[] _allColumns;
    private String _query;
    private String _saveColumns;
    private String _values;


    /* Quando chamar o objeto, passar o nome da tabela */
    public Database(String tableName) {
        this.conn = _Connection.connect();
        this.table = tableName;
        this.alias = "";
        this._columns = new String[]{"*"};
        this._allColumns = new String[]{"*"};

        this._joinConditions = new ArrayList<>();
        this._whereConditions = new ArrayList<>();
        this._query = "";
        this._saveColumns = "";
        this._values = "";
    }

    public Database(String tableName, String alias) {
        this.conn = _Connection.connect();
        this.table = tableName;
        this.alias = " AS " + alias;

        this._columns = new String[]{"*"};
        this._allColumns = new String[]{"*"};

        this._joinConditions = new ArrayList<>();
        this._whereConditions = new ArrayList<>();
        this._query = "";
    }

    /*
    ===============================================
                    FUNÇÕES GERAIS
    ===============================================
     */

    /**
     * etorna e define todas as colunas
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
     * returns the tableName
     *
     * @return table name
     */
    public String getTable() {
        return this.table;
    }

    public Database Alias(String alias){
        this.alias = alias;
        return this;
    }

    public String getAlias(){
        return this.alias;
    }

    /**
     * Método para limpar a query
     */
    private void Clear() {
        this._joinConditions = new ArrayList<>();
        this._whereConditions = new ArrayList<>();
        this._query = "";
        this._values = "";
        this._saveColumns = "";
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
                       COLUNAS
    ===============================================
     */

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

    public String col(String coluna) throws SQLException, DatabaseFault {
        String[] _colunas = get_allColumns();

        String[] _filteredArray = Arrays.stream(_colunas)
                .filter(item -> item.equals(coluna)).toArray(String[]::new);

        if(_filteredArray.length == 0)
            throw new DatabaseFault(String.format("the column: %s does not exist in table: %s", coluna, this.getTable()));

        return this.getTable() + "." + _filteredArray[0];
    }

    /**
     * Função que define e retorna todas as colunas
     *
     * @return
     * @throws SQLException
     */
    private String[] get_allColumns() throws SQLException {
        if (this._allColumns[0].equals("*")) {
            this._allColumns = selectAllColumns();
        }

        return this._allColumns;
    }

    /**
     * Função que retorna o nome das colunas desejadas da forma correta
     */
    private String[] selectAllColumns() throws SQLException {
        // Define as colunas como colunas antigas, define a nova coluna como Tudo
        String[] oldCols = this._columns;

        // Definir como todas as colunas e retornar colunas
        ResultSet rs = this.Columns(this._allColumns)._Select();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        int cont = 0;

        // Criar uma nova lista de arrays
        List arrList = new ArrayList();

        while (cont < columnCount) {
            ++cont;
            String columnName = metaData.getColumnLabel(cont);
            arrList.add(columnName);
        }

        // Voltar com as colunas anteriores e limpar a query
        this.Columns(oldCols);
        this.Clear();

        // retornar o valor
        Object[] _columnsHelper = arrList.toArray();
        return Arrays.copyOf(_columnsHelper, _columnsHelper.length, String[].class);
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
     * @throws DatabaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String condition) throws DatabaseFault {
        String[] item = new String[2];
        if (Utils.validateString(condition)) {
            item[0] = condition;
            item[1] = "AND";
        } else
            throw new DatabaseFault("Fill the fields");

        return setWhere(item);
    }

    /**
     * Define uma condição "where column = value"
     *
     * @param column coluna desejada
     * @param value  valor da coluna
     * @return instancia do objeto database
     * @throws DatabaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String value) throws DatabaseFault {
        String[] item = new String[4];
        if (Utils.validateString(column) && Utils.validateString(value)) {
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
            item[3] = "AND";
        } else {
            throw new DatabaseFault("Fill the fields");
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
     * @throws DatabaseFault Pode retornar um erro de string vazia
     */
    public Database Where(String column, String operator, String value) throws DatabaseFault {
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
     * @throws DatabaseFault
     */
    public Database WhereOr(String column, String value) throws DatabaseFault {
        String[] item = new String[4];
        if (Utils.validateString(column) && Utils.validateString(value)) {
            item[0] = column;
            item[1] = "=";
            item[2] = '"' + value + '"';
            item[3] = "OR";
        } else {
            throw new DatabaseFault("Fill the fields");
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
        _query.put("table", this.table);
        _query.put("alias", this.alias);
        _query.put("joins", this.getJoins().toString());
        _query.put("where", this.getWhere().toString());

        return _query;
    }

    /**
     * Função que executa um Select e retorna um ResultSet
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public String Select() throws SQLException, JsonProcessingException {
        return _SelectToString();
    }

    /**
     * Função que executa um Select e retorna um ResultSet com somente 1 resultado
     *
     * @return ResultSet com os dados consultados
     * @throws SQLException
     */
    public String SelectFirst() throws SQLException, JsonProcessingException {
        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${alias} ${joins} ${where} LIMIT 1");

        return _SelectToString();
    }

    /**
     * Método que executa um select e um ResultSet
     *
     * @return
     * @throws SQLException
     * @throws JsonProcessingException
     */
    private ResultSet _Select() throws SQLException {
        StringSubstitutor substitutor = new StringSubstitutor(SelectMap());
        this._query = substitutor.replace("SELECT ${columns} FROM ${table} ${alias} ${joins} ${where}");

        return execute();
    }

    /**
     * Métoodo que converte o resultSet para String
     *
     * @return
     * @throws SQLException
     * @throws JsonProcessingException
     */
    private String _SelectToString() throws SQLException, JsonProcessingException {
        ResultSet rs = _Select();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Map<String, Object>> resultList = new ArrayList<>();

        if (rs.next()) {
            do {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = rs.getObject(i);

                    if (columnValue instanceof LocalDateTime) {
                        columnValue = objectMapper.writeValueAsString(columnValue);
                    }

                    row.put(columnName, columnValue);
                }

                resultList.add(row);
            } while (rs.next());
        }

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

    /*
    ===============================================
                  JOINS
    ===============================================
     */


    /**
     * Function that add a join into join conditions
     *
     * @param join
     * @return
     */
    private Database setJoin(String[] join) {
        this._joinConditions.add(join);
        return this;
    }

    /**
     * Função que retorna todos os join
     *
     * @return String builder com todos os joins
     */
    private StringBuilder getJoins() {
        StringBuilder joinConditionBuilder = new StringBuilder();

        for (String[] condition : this._joinConditions) {
            String joinCondition = String.format("%s %s %s ON %s.%s %s %s.%s ", condition[0], condition[1], condition[2], this.getTable(), condition[3], condition[4], condition[1], condition[5]);

            joinConditionBuilder.append(joinCondition);
        }

        return joinConditionBuilder;
    }

    /**
     * function that set all table coditions for join
     *
     * @param joinType        right, left, inner, outer
     * @param table           the database instace of join table
     * @param tableColumn     the actual table colum
     * @param operator        the operator or the join table column
     * @param joinTableColumn the join table column
     */
    private Database Join(String joinType, Database table, String tableColumn, String operator, String joinTableColumn) {
        joinType = joinType.toUpperCase();
        String[] join = new String[6];
        String[] _joinCombinations = new String[]{"LEFT", "INNER", "RIGHT"};

        Arrays.parallelSort(_joinCombinations);
        int index = Arrays.binarySearch(_joinCombinations, joinType);

        join[0] = _joinCombinations[index] + " JOIN";
        join[1] = table.getTable();

        if(Utils.validateString(table.getAlias()))
            join[2] = " AS " + table.getAlias();
        else
            join[2] = "";

        join[3] = tableColumn;
        join[4] = operator;
        join[5] = joinTableColumn;

        return setJoin(join);
    }

    /*
     *   =============== Right Join ===============
     */

    /**
     * Executa um Right Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database RightJoin(Database table, String tableColumn, String joinTableColumn) {
        return Join("right", table, tableColumn, "=", joinTableColumn);
    }

    /**
     * Executa um Right Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param operator        operador de comparaçao
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database RightJoin(Database table, String tableColumn, String operator, String joinTableColumn) {
        return Join("right", table, tableColumn, operator, joinTableColumn);
    }

    /*
     *   =============== Left Join ===============
     */

    /**
     * Executa um Left Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database LeftJoin(Database table, String tableColumn, String joinTableColumn) {
        return Join("left", table, tableColumn, "=", joinTableColumn);
    }

    /**
     * Executa um Left Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param operator        operador de comparaçao
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database LeftJoin(Database table, String tableColumn, String operator, String joinTableColumn) {
        return Join("left", table, tableColumn, operator, joinTableColumn);
    }

    /*
     *   =============== Inner Join ===============
     */

    /**
     * Executa um Inner Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database InnerJoin(Database table, String tableColumn, String joinTableColumn) {
        return Join("inner", table, tableColumn, "=", joinTableColumn);
    }

    /**
     * Executa um Inner Join
     *
     * @param table           instancia do banco de dados da tabela do join
     * @param tableColumn     coluna que será comparada
     * @param operator        operador de comparaçao
     * @param joinTableColumn coluna da tabela join que sera comparada
     * @return uma instancia do banco de dados
     */
    public Database InnerJoin(Database table, String tableColumn, String operator, String joinTableColumn) {
        return Join("inner", table, tableColumn, operator, joinTableColumn);
    }
}