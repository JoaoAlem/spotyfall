package com.player.spotyfall.modules.database;

import org.apache.commons.text.StringSubstitutor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _Connection {
    private static Connection connection;

    private static Properties getProperties() throws IOException {
        String fileName = "database.properties";
        Properties properties = new Properties();
        _Connection connObj = new _Connection();
        InputStream inputStream = connObj.getClass().getClassLoader().getResourceAsStream(fileName);

        if(inputStream != null)
            properties.load(inputStream);
        else
            throw new FileNotFoundException("property file not found in the classpath");


        inputStream.close();
        return properties;
    }

    private static String _connectionString() throws IOException
    {
        Properties properties = getProperties();

        Map<String, String> databaseConn = new HashMap<>();
        databaseConn.put("engine", properties.getProperty("engine"));
        databaseConn.put("database", properties.getProperty("database"));
        databaseConn.put("host", properties.getProperty("host"));
        databaseConn.put("port", properties.getProperty("port"));
        databaseConn.put("username", properties.getProperty("username"));
        databaseConn.put("password", properties.getProperty("password"));

        StringSubstitutor sub = new StringSubstitutor(databaseConn);
        return sub.replace("jdbc:${engine}://${host}:${port}/${database}?user=${username}&password=${password}");
    }

    private static String _getClassName() throws IOException
    {
        Properties properties = getProperties();

        return properties.getProperty("driver");
    }

    private static Connection OpenConnection() throws SQLException, ClassNotFoundException, IOException
    {
        Class.forName(_getClassName());
        return DriverManager.getConnection(_connectionString());
    }

    public static Connection connect()
    {
        try {
            connection = OpenConnection();
            return connection;
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}