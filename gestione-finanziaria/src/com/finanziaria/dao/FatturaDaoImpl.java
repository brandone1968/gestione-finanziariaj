package com.finanziaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.finanziaria.beans.DettaglioFattura;
import com.finanziaria.beans.Ditta;
import com.finanziaria.beans.Fattura;

public class FatturaDaoImpl implements FatturaDao {

    private DaoFactory          daoFactory;
    private static final String EMESSE = "0";
    private Statement           statement;

    FatturaDaoImpl( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    public List<Fattura> lister( String tipoPagamento, String anno ) throws DaoException {
        List<Fattura> fatture = new ArrayList<Fattura>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;

        try {
            connexion = daoFactory.getConnection();

            String sql = "select fattura_id, fattura_num_fattura, descrizione, fattura_data_fattura, fattura_data_pagamento, fattura_imponibile, percentuale_IVA, fattura_iva,"
                    + "fattura_tot_fattura, fattura_note_fattura, id_ditta1, id_ditta2,"
                    + "ditta1.id_ditta as id_ditta1, ditta1.denominazione as denominazione1, ditta1.indirizzo as indirizzo1, ditta1.cap as cap1, ditta1.citta as citta1, ditta1.cf as cf1, ditta1.piva as piva1, ditta1.default_immissione as default_immissione1, ditta1.ditta_time_stamp as ditta_time_stamp1,"
                    + "ditta2.id_ditta as id_ditta2, ditta2.denominazione as denominazione2, ditta2.indirizzo as indirizzo2, ditta2.cap as cap2, ditta2.citta as citta2, ditta2.cf as cf2, ditta2.piva as piva2, ditta2.default_immissione as default_immissione2, ditta2.ditta_time_stamp as ditta_time_stamp2"
                    + " from fattura"
                    + " Join ditta as ditta1 on id_ditta1 = ditta1.id_ditta"
                    + " Join ditta as ditta2 on id_ditta2 = ditta2.id_ditta";

            if ( tipoPagamento.equals( EMESSE ) ) {
                sql = sql + " where YEAR(fattura_data_fattura)=?";
            } else {
                sql = sql + " where YEAR(fattura_data_pagamento)=?";
            }
            sql = sql + " order by fattura_id asc";

            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setString( 1, anno );
            risultato = preparedStatement.executeQuery();

            while ( risultato.next() ) {
                Integer id = risultato.getInt( "fattura_id" );
                Integer numFattura = risultato.getInt( "fattura_num_fattura" );
                String descrizione = risultato.getString( "descrizione" );

                String dataFatturaTemp = risultato.getString( "fattura_data_fattura" );
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
                LocalDate dataFattura = LocalDate.parse( dataFatturaTemp, formatter );

                String dataPagamentoTemp = risultato.getString( "fattura_data_pagamento" );
                LocalDate dataPagamento = null;
                if ( ( dataPagamentoTemp != null ) && ( !dataPagamentoTemp.equals( "" ) ) ) {
                    dataPagamento = LocalDate.parse( dataPagamentoTemp, formatter );
                }

                Float imponibile = risultato.getFloat( "fattura_imponibile" );
                Float percentualeIVA = risultato.getFloat( "percentuale_IVA" );
                Float iva = risultato.getFloat( "fattura_IVA" );
                Float totFattura = risultato.getFloat( "fattura_tot_fattura" );
                String noteFattura = risultato.getString( "fattura_note_fattura" );

                Fattura fattura = new Fattura();
                fattura.setId( id );
                fattura.setNumFattura( numFattura );
                fattura.setDescrizione( descrizione );
                fattura.setDataFattura( dataFattura );
                fattura.setDataPagamento( dataPagamento );
                fattura.setImponibile( imponibile );
                fattura.setPercentualeIVA( percentualeIVA );
                fattura.setIva( iva );
                fattura.setTotFattura( totFattura );
                fattura.setNoteFattura( noteFattura );

                Ditta dittaTmp = new Ditta();
                Integer id_ditta = risultato.getInt( "id_ditta1" );
                String denominazione = risultato.getString( "denominazione1" );
                String indirizzo = risultato.getString( "indirizzo1" );
                Integer cap = risultato.getInt( "cap1" );
                String citta = risultato.getString( "citta1" );
                String cf = risultato.getString( "cf1" );
                String piva = risultato.getString( "piva1" );
                Boolean default_immissione = risultato.getBoolean( "default_immissione1" );
                Timestamp dittaTimeStamp = risultato.getTimestamp( "ditta_time_stamp1" );

                dittaTmp.setId_ditta( id_ditta );
                dittaTmp.setDenominazione( denominazione );
                dittaTmp.setIndirizzo( indirizzo );
                dittaTmp.setCap( cap );
                dittaTmp.setCitta( citta );
                dittaTmp.setCf( cf );
                dittaTmp.setPiva( piva );
                dittaTmp.setDefault_immissione( default_immissione );
                dittaTmp.setDittaTimeStamp( dittaTimeStamp );

                fattura.setDitta1( dittaTmp );

                Ditta dittaTmp2 = new Ditta();
                Integer id_ditta2 = risultato.getInt( "id_ditta2" );
                String denominazione2 = risultato.getString( "denominazione2" );
                String indirizzo2 = risultato.getString( "indirizzo2" );
                Integer cap2 = risultato.getInt( "cap2" );
                String citta2 = risultato.getString( "citta2" );
                String cf2 = risultato.getString( "cf2" );
                String piva2 = risultato.getString( "piva2" );
                Boolean default_immissione2 = risultato.getBoolean( "default_immissione2" );
                Timestamp dittaTimeStamp2 = risultato.getTimestamp( "ditta_time_stamp2" );

                dittaTmp2.setId_ditta( id_ditta2 );
                dittaTmp2.setDenominazione( denominazione2 );
                dittaTmp2.setIndirizzo( indirizzo2 );
                dittaTmp2.setCap( cap2 );
                dittaTmp2.setCitta( citta2 );
                dittaTmp2.setCf( cf2 );
                dittaTmp2.setPiva( piva2 );
                dittaTmp2.setDefault_immissione( default_immissione2 );
                dittaTmp2.setDittaTimeStamp( dittaTimeStamp2 );

                fattura.setDitta1( dittaTmp );
                fattura.setDitta2( dittaTmp2 );

                fatture.add( fattura );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati" );
            }
        }
        return fatture;
    }

    public List<String> findAllYearIssue() {
        List<String> anniSelezionabili = new ArrayList<String>();
        Connection connexion = null;
        statement = null;
        ResultSet risultato = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select distinct YEAR(fattura_data_fattura) as annoSelezionabile from fattura order by fattura_data_fattura desc";

            // Esecuzione della richiesta
            risultato = statement.executeQuery( sql );

            // Recupero i dati
            while ( risultato.next() ) {
                String annoSelezionabile = risultato.getString( "annoSelezionabile" );
                anniSelezionabili.add( annoSelezionabile );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati" );
            }
        }
        return anniSelezionabili;
    }

    public List<String> findAllYearBalance() {
        List<String> anniSelezionabili = new ArrayList<String>();
        Connection connexion = null;
        statement = null;
        ResultSet risultato = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select distinct YEAR(fattura_data_pagamento) as annoSelezionabile from fattura"
                    + " where fattura_data_pagamento IS NOT NULL order by annoSelezionabile desc";

            // Esecuzione della richiesta
            risultato = statement.executeQuery( sql );

            // Recupero i dati
            while ( risultato.next() ) {
                String annoSelezionabile = risultato.getString( "annoSelezionabile" );
                anniSelezionabili.add( annoSelezionabile );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati" );
            }
        }
        return anniSelezionabili;
    }

    public Fattura find( Integer idFattura ) {
        DettaglioFatturaDao dettaglioFatturaDao;
        DaoFactory daoFactory = DaoFactory.getInstance();
        dettaglioFatturaDao = daoFactory.getDettaglioFatturaDao();

        Fattura fattura = new Fattura();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;

        try {
            connexion = daoFactory.getConnection();

            String sql = "select fattura_id, fattura_num_fattura, descrizione, fattura_data_fattura, fattura_data_pagamento, fattura_imponibile, percentuale_IVA, fattura_iva, fattura_tot_fattura, fattura_note_fattura, id_ditta1, id_ditta2, "
                    + "ditta1.id_ditta as id_ditta1, ditta1.denominazione as denominazione1, ditta1.indirizzo as indirizzo1, ditta1.cap as cap1, ditta1.citta as citta1, ditta1.cf as cf1, ditta1.piva as piva1, ditta1.default_immissione as default_immissione1, ditta1.ditta_time_stamp as ditta_time_stamp1,"
                    + "ditta2.id_ditta as id_ditta2, ditta2.denominazione as denominazione2, ditta2.indirizzo as indirizzo2, ditta2.cap as cap2, ditta2.citta as citta2, ditta2.cf as cf2, ditta2.piva as piva2, ditta2.default_immissione as default_immissione2, ditta2.ditta_time_stamp as ditta_time_stamp2"
                    + " from fattura"
                    + " Join ditta as ditta1 on id_ditta1 = ditta1.id_ditta"
                    + " Join ditta as ditta2 on id_ditta2 = ditta2.id_ditta"
                    + " where fattura_id=?";

            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setInt( 1, idFattura );
            risultato = preparedStatement.executeQuery();
            risultato.beforeFirst();
            if ( risultato.next() ) {

                Integer id = risultato.getInt( "fattura_id" );
                Integer numFattura = risultato.getInt( "fattura_num_fattura" );
                String descrizione = risultato.getString( "descrizione" );

                String dataFatturaTemp = risultato.getString( "fattura_data_fattura" );
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
                LocalDate dataFattura = LocalDate.parse( dataFatturaTemp, formatter );

                String dataPagamentoTemp = risultato.getString( "fattura_data_pagamento" );
                LocalDate dataPagamento = null;
                if ( ( dataPagamentoTemp != null ) && ( !dataPagamentoTemp.equals( "" ) ) ) {
                    dataPagamento = LocalDate.parse( dataPagamentoTemp, formatter );
                }

                Float imponibile = risultato.getFloat( "fattura_imponibile" );
                Float percentualeIVA = risultato.getFloat( "percentuale_IVA" );
                Float iva = risultato.getFloat( "fattura_IVA" );
                Float totFattura = risultato.getFloat( "fattura_tot_fattura" );
                String noteFattura = risultato.getString( "fattura_note_fattura" );

                fattura.setId( id );
                fattura.setNumFattura( numFattura );
                fattura.setDescrizione( descrizione );
                fattura.setDataFattura( dataFattura );
                fattura.setDataPagamento( dataPagamento );
                fattura.setImponibile( imponibile );
                fattura.setPercentualeIVA( percentualeIVA );
                fattura.setIva( iva );
                fattura.setTotFattura( totFattura );
                fattura.setNoteFattura( noteFattura );

                Ditta dittaTmp = new Ditta();
                Integer id_ditta = risultato.getInt( "id_ditta1" );
                String denominazione = risultato.getString( "denominazione1" );
                String indirizzo = risultato.getString( "indirizzo1" );
                Integer cap = risultato.getInt( "cap1" );
                String citta = risultato.getString( "citta1" );
                String cf = risultato.getString( "cf1" );
                String piva = risultato.getString( "piva1" );
                Boolean default_immissione = risultato.getBoolean( "default_immissione1" );
                Timestamp dittaTimeStamp = risultato.getTimestamp( "ditta_time_stamp1" );

                dittaTmp.setId_ditta( id_ditta );
                dittaTmp.setDenominazione( denominazione );
                dittaTmp.setIndirizzo( indirizzo );
                dittaTmp.setCap( cap );
                dittaTmp.setCitta( citta );
                dittaTmp.setCf( cf );
                dittaTmp.setPiva( piva );
                dittaTmp.setDefault_immissione( default_immissione );
                dittaTmp.setDittaTimeStamp( dittaTimeStamp );

                fattura.setDitta1( dittaTmp );

                Ditta dittaTmp2 = new Ditta();
                Integer id_ditta2 = risultato.getInt( "id_ditta2" );
                String denominazione2 = risultato.getString( "denominazione2" );
                String indirizzo2 = risultato.getString( "indirizzo2" );
                Integer cap2 = risultato.getInt( "cap2" );
                String citta2 = risultato.getString( "citta2" );
                String cf2 = risultato.getString( "cf2" );
                String piva2 = risultato.getString( "piva2" );
                Boolean default_immissione2 = risultato.getBoolean( "default_immissione2" );
                Timestamp dittaTimeStamp2 = risultato.getTimestamp( "ditta_time_stamp2" );

                dittaTmp2.setId_ditta( id_ditta2 );
                dittaTmp2.setDenominazione( denominazione2 );
                dittaTmp2.setIndirizzo( indirizzo2 );
                dittaTmp2.setCap( cap2 );
                dittaTmp2.setCitta( citta2 );
                dittaTmp2.setCf( cf2 );
                dittaTmp2.setPiva( piva2 );
                dittaTmp2.setDefault_immissione( default_immissione2 );
                dittaTmp2.setDittaTimeStamp( dittaTimeStamp2 );

                fattura.setDitta1( dittaTmp );
                fattura.setDitta2( dittaTmp2 );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati" );
            }
        }

        // ricavo i dettagli della fattura
        fattura.setDettagliFattura( dettaglioFatturaDao.findAllByFattura( idFattura ) );

        return fattura;
    }

    public Fattura impostaFatturaDefault() {

        Fattura fattura = new Fattura();
        // Imposto a 1 il numero della prossima fattura nel caso non ci siano
        // altre fatture dell'anno in corso
        // nel db
        Integer numeroProssimaFattura = 1;

        Connection connexion = null;
        ResultSet risultato = null;

        // Imposto la data del giorno
        fattura.setDataFattura( LocalDate.now() );

        // ricavo il primo numero di fattura libero per l'anno in corso
        try {

            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            String sql = "select MAX(fattura_num_fattura) as num_fattura from fattura WHERE YEAR(fattura_data_fattura) = '"
                    + LocalDate.now().getYear() + "'";

            // Esecuzione della richiesta
            risultato = statement.executeQuery( sql );
            risultato.beforeFirst();

            // Recupero i dati
            if ( risultato.next() ) {
                numeroProssimaFattura = risultato.getInt( "num_fattura" ) + 1;
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati " );
            }
        }

        fattura.setNumFattura( numeroProssimaFattura );

        // imposto almeno un dettaglio
        DettaglioFattura dettaglio = new DettaglioFattura();
        dettaglio.setId( 1 );
        List<DettaglioFattura> dettagli = new ArrayList<DettaglioFattura>();
        dettagli.add( dettaglio );
        fattura.setDettagliFattura( dettagli );
        return fattura;
    }

    public Fattura find( Integer num, Integer annoFattura ) throws SQLException {
        DettaglioFatturaDao dettaglioFatturaDao;
        DaoFactory daoFactory = DaoFactory.getInstance();
        dettaglioFatturaDao = daoFactory.getDettaglioFatturaDao();

        Fattura fattura = new Fattura();

        Connection connexion = null;
        connexion = daoFactory.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;

        try {
            connexion = daoFactory.getConnection();

            String sql = "select fattura_id, fattura_num_fattura, descrizione, fattura_data_fattura, fattura_data_pagamento, fattura_imponibile, percentuale_IVA, fattura_iva, fattura_tot_fattura, fattura_note_fattura, id_ditta1, id_ditta2, "
                    + "ditta1.id_ditta as id_ditta1, ditta1.denominazione as denominazione1, ditta1.indirizzo as indirizzo1, ditta1.cap as cap1, ditta1.citta as citta1, ditta1.cf as cf1, ditta1.piva as piva1, ditta1.default_immissione as default_immissione1, ditta1.ditta_time_stamp as ditta_time_stamp1,"
                    + "ditta2.id_ditta as id_ditta2, ditta2.denominazione as denominazione2, ditta2.indirizzo as indirizzo2, ditta2.cap as cap2, ditta2.citta as citta2, ditta2.cf as cf2, ditta2.piva as piva2, ditta2.default_immissione as default_immissione2, ditta2.ditta_time_stamp as ditta_time_stamp2"
                    + " from fattura"
                    + " Join ditta as ditta1 on id_ditta1 = ditta1.id_ditta"
                    + " Join ditta as ditta2 on id_ditta2 = ditta2.id_ditta"
                    + " where fattura_num_fattura=? and YEAR(fattura_data_fattura)=?";

            preparedStatement = connexion.prepareStatement( sql );
            preparedStatement.setInt( 1, num );
            preparedStatement.setInt( 2, annoFattura );
            risultato = preparedStatement.executeQuery();
            risultato.beforeFirst();
            if ( risultato.next() ) {

                Integer id = risultato.getInt( "fattura_id" );
                Integer numFattura = risultato.getInt( "fattura_num_fattura" );
                String descrizione = risultato.getString( "descrizione" );

                String dataFatturaTemp = risultato.getString( "fattura_data_fattura" );
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
                LocalDate dataFattura = LocalDate.parse( dataFatturaTemp, formatter );

                String dataPagamentoTemp = risultato.getString( "fattura_data_pagamento" );
                LocalDate dataPagamento = null;
                if ( ( dataPagamentoTemp != null ) && ( !dataPagamentoTemp.equals( "" ) ) ) {
                    dataPagamento = LocalDate.parse( dataPagamentoTemp, formatter );
                }

                Float imponibile = risultato.getFloat( "fattura_imponibile" );
                Float percentualeIVA = risultato.getFloat( "percentuale_IVA" );
                Float iva = risultato.getFloat( "fattura_IVA" );
                Float totFattura = risultato.getFloat( "fattura_tot_fattura" );
                String noteFattura = risultato.getString( "fattura_note_fattura" );

                fattura.setId( id );
                fattura.setNumFattura( numFattura );
                fattura.setDescrizione( descrizione );
                fattura.setDataFattura( dataFattura );
                fattura.setDataPagamento( dataPagamento );
                fattura.setImponibile( imponibile );
                fattura.setPercentualeIVA( percentualeIVA );
                fattura.setIva( iva );
                fattura.setTotFattura( totFattura );
                fattura.setNoteFattura( noteFattura );

                Ditta dittaTmp = new Ditta();
                Integer id_ditta = risultato.getInt( "id_ditta1" );
                String denominazione = risultato.getString( "denominazione1" );
                String indirizzo = risultato.getString( "indirizzo1" );
                Integer cap = risultato.getInt( "cap1" );
                String citta = risultato.getString( "citta1" );
                String cf = risultato.getString( "cf1" );
                String piva = risultato.getString( "piva1" );
                Boolean default_immissione = risultato.getBoolean( "default_immissione1" );
                Timestamp dittaTimeStamp = risultato.getTimestamp( "ditta_time_stamp1" );

                dittaTmp.setId_ditta( id_ditta );
                dittaTmp.setDenominazione( denominazione );
                dittaTmp.setIndirizzo( indirizzo );
                dittaTmp.setCap( cap );
                dittaTmp.setCitta( citta );
                dittaTmp.setCf( cf );
                dittaTmp.setPiva( piva );
                dittaTmp.setDefault_immissione( default_immissione );
                dittaTmp.setDittaTimeStamp( dittaTimeStamp );

                fattura.setDitta1( dittaTmp );

                Ditta dittaTmp2 = new Ditta();
                Integer id_ditta2 = risultato.getInt( "id_ditta2" );
                String denominazione2 = risultato.getString( "denominazione2" );
                String indirizzo2 = risultato.getString( "indirizzo2" );
                Integer cap2 = risultato.getInt( "cap2" );
                String citta2 = risultato.getString( "citta2" );
                String cf2 = risultato.getString( "cf2" );
                String piva2 = risultato.getString( "piva2" );
                Boolean default_immissione2 = risultato.getBoolean( "default_immissione2" );
                Timestamp dittaTimeStamp2 = risultato.getTimestamp( "ditta_time_stamp2" );

                dittaTmp2.setId_ditta( id_ditta2 );
                dittaTmp2.setDenominazione( denominazione2 );
                dittaTmp2.setIndirizzo( indirizzo2 );
                dittaTmp2.setCap( cap2 );
                dittaTmp2.setCitta( citta2 );
                dittaTmp2.setCf( cf2 );
                dittaTmp2.setPiva( piva2 );
                dittaTmp2.setDefault_immissione( default_immissione2 );
                dittaTmp2.setDittaTimeStamp( dittaTimeStamp2 );

                fattura.setDitta1( dittaTmp );
                fattura.setDitta2( dittaTmp2 );

                // ricavo i dettagli della fattura
                fattura.setDettagliFattura( dettaglioFatturaDao.findAllByFattura( id ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( "Impossibile comunicare con la base dati " + e );
        } finally {
            try {
                if ( connexion != null ) {
                    connexion.close();
                }
            } catch ( SQLException e ) {
                throw new DaoException( "Impossibile comunicare con la base dati" );
            }
        }

        return fattura;
    }

    public void creaFattura( Fattura fattura ) throws SQLException {
        //
        // occorre inserire i dati sia nella tabella fattura che nella tabella
        // dettagli
        // Utilizzare la trasaction per definire un blocco di query da eseguire
        // in caso di errore rolback automatico
        // vedi:
        // bConnection.setAutoCommit(false); to start a transaction block.
        // dbConnection.commit(); to end a transaction block.

        // Prima di inserire la fattura occorre verificare che non ci sia già
        // una fattura con lo stesso numero e anno
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet risultato = null;

        // calcolo il valore del timestamp, in modo che sia identico per tutta
        // la fattura
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp valoreTimestamp = new java.sql.Timestamp( calendar.getTime().getTime() );

        // Inserimento fattura
        try {

            connexion = daoFactory.getConnection();
            connexion.setAutoCommit( false );

            String sql = "INSERT INTO fattura (fattura_num_fattura, descrizione, fattura_data_fattura, fattura_data_pagamento, fattura_imponibile, percentuale_IVA, fattura_iva, fattura_tot_fattura , fattura_note_fattura, id_ditta1, id_ditta2  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connexion.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setInt( 1, fattura.getNumFattura() );
            preparedStatement.setString( 2, fattura.getDescrizione() );
            preparedStatement.setString( 3, fattura.getDataFattura().toString() );
            if ( fattura.getDataPagamento() != null ) {
                preparedStatement.setString( 4, fattura.getDataPagamento().toString() );
            } else {
                preparedStatement.setString( 4, null );
            }
            preparedStatement.setFloat( 5, fattura.getImponibile() );
            preparedStatement.setFloat( 6, fattura.getPercentualeIVA() );
            preparedStatement.setFloat( 7, fattura.getIva() );
            preparedStatement.setFloat( 8, fattura.getTotFattura() );
            preparedStatement.setString( 9, fattura.getNoteFattura() );
            preparedStatement.setInt( 10, fattura.getDitta1().getId_ditta() );
            preparedStatement.setInt( 11, fattura.getDitta2().getId_ditta() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DaoException( "Errore nell'inserimento dei dati" );
            }
            /* recupero l'id inserito */
            risultato = preparedStatement.getGeneratedKeys();
            int id = 1;
            if ( risultato.next() ) {
                /* inizializzazione della proprità id della fattura */
                // fattura.setId( risultato.getInt( 1 ) );
                id = risultato.getInt( 1 );
                // Salvo i dettagli della fattura
            } else {
                throw new DaoException( "Errore nell'inserimento dei dati, nessun ID di inserimento recuperato." );
            }

            for ( DettaglioFattura dettaglioFattura : fattura.getDettagliFattura() ) {
                //
                // Inserimento dettaglio fattura

                String sql2 = "INSERT INTO dettaglio_fattura (descrizione, qta, unita_misura_qta, tariffa, dettaglio_fattura_time_stamp, fattura_id ) VALUES (?, ?, ?, ?, ?, ?)";

                preparedStatement = connexion.prepareStatement( sql2, Statement.RETURN_GENERATED_KEYS );
                preparedStatement.setString( 1, dettaglioFattura.getDescrizione() );
                preparedStatement.setInt( 2, dettaglioFattura.getQta() );
                preparedStatement.setInt( 3, dettaglioFattura.getUnitaMisuraQta() );
                preparedStatement.setFloat( 4, dettaglioFattura.getTariffa() );
                preparedStatement.setTimestamp( 5, valoreTimestamp );
                preparedStatement.setInt( 6, id );

                int statut2 = preparedStatement.executeUpdate();
                /* Analyse du statut retourné par la requête d'insertion */
                if ( statut2 == 0 ) {
                    throw new DaoException( "Errore nell'inserimento dei dati" );
                }
                /* recupero l'id inserito */
                risultato = preparedStatement.getGeneratedKeys();
                if ( risultato.next() ) {
                    /* inizializzazione della proprità id della fattura */
                    fattura.setId( risultato.getInt( 1 ) );
                } else {
                    throw new DaoException( "Errore nell'inserimento dei dati, nessun ID di inserimento recuperato." );
                }
            } // chiude ciclo dettaglio fattura

            // Salvo la fattura e i relativi dettagli
            connexion.commit();

        } catch ( SQLException e ) {
            if ( connexion != null ) {
                connexion.rollback();
                System.out.println( "Connection rollback..." );
            }

            e.printStackTrace();

        } finally {
            if ( connexion != null && !connexion.isClosed() ) {
                connexion.close();
            }
        }

    }

}