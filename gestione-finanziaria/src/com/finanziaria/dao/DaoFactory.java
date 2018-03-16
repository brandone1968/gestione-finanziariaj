package com.finanziaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/gestfin", "gestfin_user", "secret" );
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection( url, username, password );
        connexion.setAutoCommit( false );
        return connexion;
    }

    // Recupero Dao
    public ScadenzaDao getScadenzaDao() {
        return new ScadenzaDaoImpl( this );
    }

    // Recupero Dao
    public FatturaDao getFatturaDao() {
        return new FatturaDaoImpl( this );
    }

    // Recupero Dao
    public DettaglioFatturaDao getDettaglioFatturaDao() {
        return new DettaglioFatturaDaoImpl( this );
    }

    // Recupero Dao
    public DittaDao getDittaDao() {
        return new DittaDaoImpl( this );
    }
}
