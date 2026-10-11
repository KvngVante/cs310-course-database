package edu.jsu.mcis.cs310.dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DAOFactory {
    
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    
    private final String url, username, password;
    private Connection conn;
    
    public DAOFactory(String prefix) {

        DAOProperties properties = new DAOProperties(prefix);

        this.url = properties.getProperty(PROPERTY_URL);
        this.username = properties.getProperty(PROPERTY_USERNAME);
        this.password = properties.getProperty(PROPERTY_PASSWORD);
        
        try {
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            conn = null;
            e.printStackTrace();
        }
        
    }
    
    Connection getConnection() {
        return conn;
    }
    
    public PersonDAO getPersonDAO() {
        return new PersonDAO(this);
    }
    
}