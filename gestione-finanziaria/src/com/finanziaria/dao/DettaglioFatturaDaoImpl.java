package com.finanziaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.finanziaria.beans.DettaglioFattura;

public class DettaglioFatturaDaoImpl implements DettaglioFatturaDao {

    private DaoFactory daoFactory;

    DettaglioFatturaDaoImpl( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    public List<DettaglioFattura> findAllByFattura( Integer idFattura ) throws DaoException {
        List<DettaglioFattura> dettagliFattura = new ArrayList<DettaglioFattura>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "select dettaglio_id, descrizione, qta, unita_misura_qta, tariffa, dettaglio_fattura_time_stamp from dettaglio_fattura where fattura_id=? order by dettaglio_id";
            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setInt( 1, idFattura );
            resultat = preparedStatement.executeQuery();

            while ( resultat.next() ) {

                Integer id = resultat.getInt( "dettaglio_id" );
                String descrizione = resultat.getString( "descrizione" );
                Integer qta = resultat.getInt( "qta" );
                Integer unitaMisuraQta = resultat.getInt( "unita_misura_qta" );
                Float tariffa = resultat.getFloat( "tariffa" );
                Timestamp dettaglioFatturaTimeStamp = resultat.getTimestamp( "dettaglio_fattura_time_stamp" );

                DettaglioFattura dettaglioFattura = new DettaglioFattura();
                dettaglioFattura.setId( id );
                dettaglioFattura.setDescrizione( descrizione );
                dettaglioFattura.setQta( qta );
                dettaglioFattura.setUnitaMisuraQta( unitaMisuraQta );
                dettaglioFattura.setTariffa( tariffa );
                dettaglioFattura.setDettaglioFatturaTimeStamp( dettaglioFatturaTimeStamp );

                dettagliFattura.add( dettaglioFattura );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossible de communiquer avec la base de données" );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossible de communiquer avec la base de données" );
            }
        }
        return dettagliFattura;
    }

}