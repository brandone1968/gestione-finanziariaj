package com.finanziaria.dao;

import java.sql.SQLException;
import java.util.List;

import com.finanziaria.beans.Fattura;

public interface FatturaDao {

    // metodo per elencare le Fatture
    List<Fattura> lister( String tipoPagamento, String anno ) throws DaoException;

    // metodo per trovare gli anni disponibili delle fatture emesse
    List<String> findAllYearIssue() throws DaoException;

    // metodo per trovare gli anni di pagamento delle fatture
    List<String> findAllYearBalance() throws DaoException;

    // metodo per ricavare i dati di una fattura dato l'id
    Fattura find( Integer id ) throws DaoException;

    // metodo per creare una fattura base con il primo numero libero per una
    // nuova fattura
    Fattura impostaFatturaDefault() throws DaoException;

    // metodo per ricavare una fattura dato numero fattura e anno della stessa
    Fattura find( Integer num, Integer anno ) throws SQLException;

    // metodo per ricavare una fattura dato numero fattura e data della stessa
    // Fattura find( Integer num, LocalDate data);

    // metodo per inserire la fattura
    void creaFattura( Fattura fattura ) throws SQLException;
}
