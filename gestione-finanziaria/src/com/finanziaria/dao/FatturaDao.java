package com.finanziaria.dao;

import java.util.List;

import com.finanziaria.beans.Fattura;

public interface FatturaDao {

    // metodo per elencare le Fatture
    List<Fattura> lister( String tipoPagamento, String anno ) throws DaoException;

    // metodo per trovare gli anni disponibili delle fatture emesse
    List<String> findAllYearIssue() throws DaoException;

    // metodo per trovare gli anni di pagamento delle fatture
    List<String> findAllYearBalance() throws DaoException;

    // metodo per ricavare i dati di una fattura
    Fattura find( Integer id ) throws DaoException;

    // metodo per ricavare il primo numero libero per una nuova fattura
    Fattura impostaFatturaDefault() throws DaoException;
}
