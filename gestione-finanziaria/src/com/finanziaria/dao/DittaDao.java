package com.finanziaria.dao;

import java.util.List;

import com.finanziaria.beans.Ditta;

public interface DittaDao {

    // metodo per elencare le Ditte
    List<Ditta> lista() throws DaoException;

    // metodo per sapere quale ditta è impostata come default per emettere le
    // fatture
    Integer findDittaDefault( Integer tipoRichiesta ) throws DaoException;

    // metodo che dato un id ricava tutti i dati di quella ditta
    Ditta findDatiDitta( Integer idDitta ) throws DaoException;
}
