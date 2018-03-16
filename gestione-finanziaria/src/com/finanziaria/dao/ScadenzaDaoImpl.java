package com.finanziaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.finanziaria.beans.Scadenza;

public class ScadenzaDaoImpl implements ScadenzaDao {

    private DaoFactory daoFactory;

    ScadenzaDaoImpl( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    public List<Scadenza> lister() throws DaoException {
        List<Scadenza> scadenze = new ArrayList<Scadenza>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        // Ricavo il numero del mese corrente
        int meseCorrente = LocalDate.now().getMonthValue();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(
                    "SELECT id, descrizione, data_scadenza FROM scadenza where DATE_FORMAT(data_scadenza,'%m') = ? order by DATE_FORMAT(data_scadenza,'%m') asc" );
            preparedStatement.setInt( 1, meseCorrente );
            resultat = preparedStatement.executeQuery();

            while ( resultat.next() ) {
                Integer id = resultat.getInt( "id" );
                String descrizione = resultat.getString( "descrizione" );
                String dataScadenzaTemp = resultat.getString( "data_scadenza" );
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
                LocalDate dataScadenza = LocalDate.parse( dataScadenzaTemp, formatter );

                Scadenza scadenza = new Scadenza();
                scadenza.setId( id );
                scadenza.setDescrizione( descrizione );
                scadenza.setDataScadenza( dataScadenza );

                scadenze.add( scadenza );
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
        return scadenze;
    }

}