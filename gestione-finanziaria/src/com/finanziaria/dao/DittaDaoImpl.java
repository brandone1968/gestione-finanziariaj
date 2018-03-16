package com.finanziaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.finanziaria.beans.Ditta;

public class DittaDaoImpl implements DittaDao {

    private DaoFactory daoFactory;
    private Statement  statement;

    DittaDaoImpl( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    public List<Ditta> lista() {
        List<Ditta> elencoDitte = new ArrayList<Ditta>();
        Connection connexion = null;
        statement = null;
        ResultSet resultat = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select id_ditta, denominazione, indirizzo, cap, citta, cf , piva, default_immissione,"
                    + " ditta_time_stamp from ditta order by denominazione asc";

            // Esecuzione della richiesta
            resultat = statement.executeQuery( sql );

            // Recupero i dati
            while ( resultat.next() ) {
                Integer id_ditta = resultat.getInt( "id_ditta" );
                String denominazione = resultat.getString( "denominazione" );
                String indirizzo = resultat.getString( "indirizzo" );
                Integer cap = resultat.getInt( "cap" );
                String citta = resultat.getString( "citta" );
                String cf = resultat.getString( "cf" );
                String piva = resultat.getString( "piva" );
                Boolean default_immissione = resultat.getBoolean(
                        "default_immissione" );
                Timestamp dittaTimeStamp = resultat.getTimestamp(
                        "ditta_time_stamp" );

                Ditta ditta = new Ditta();

                ditta.setId_ditta( id_ditta );
                ditta.setDenominazione( denominazione );
                ditta.setIndirizzo( indirizzo );
                ditta.setCap( cap );
                ditta.setCitta( citta );
                ditta.setCf( cf );
                ditta.setPiva( piva );
                ditta.setDefault_immissione( default_immissione );
                ditta.setDittaTimeStamp( dittaTimeStamp );

                elencoDitte.add( ditta );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible comunicare col la base dati" + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossible comunicare col la base dati" + e );
            }
        }
        return elencoDitte;
    }

    public Integer findDittaDefault( Integer tipoRichiesta ) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;
        Integer codiceDitta = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select id_ditta from ditta where default_immissione =?";

            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setInt( 1, tipoRichiesta );
            risultato = preparedStatement.executeQuery();
            risultato.beforeFirst();
            if ( risultato.next() ) {
                codiceDitta = risultato.getInt( "id_ditta" );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossible comunicare con la base dati " + e );
            }
        }
        return codiceDitta;
    }

    public Ditta findDatiDitta( Integer idDitta ) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;
        Ditta datiDitta = new Ditta();

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select id_ditta, denominazione, indirizzo, cap, citta, cf, piva, default_immissione, ditta_time_stamp  from ditta where id_ditta =?";

            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setInt( 1, idDitta );
            risultato = preparedStatement.executeQuery();
            risultato.beforeFirst();
            if ( risultato.next() ) {
                Integer id_ditta = risultato.getInt( "id_ditta" );
                String denominazione = risultato.getString( "denominazione" );
                String indirizzo = risultato.getString( "indirizzo" );
                Integer cap = risultato.getInt( "cap" );
                String citta = risultato.getString( "citta" );
                String cf = risultato.getString( "cf" );
                String piva = risultato.getString( "piva" );
                Boolean default_immissione = risultato.getBoolean( "default_immissione" );
                Timestamp dittaTimeStamp = risultato.getTimestamp( "ditta_time_stamp" );

                datiDitta.setId_ditta( id_ditta );
                datiDitta.setDenominazione( denominazione );
                datiDitta.setIndirizzo( indirizzo );
                datiDitta.setCap( cap );
                datiDitta.setCitta( citta );
                datiDitta.setCf( cf );
                datiDitta.setPiva( piva );
                datiDitta.setDefault_immissione( default_immissione );
                datiDitta.setDittaTimeStamp( dittaTimeStamp );

            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossible comunicare con la base dati " + e );
            }
        }

        return datiDitta;
    }
}