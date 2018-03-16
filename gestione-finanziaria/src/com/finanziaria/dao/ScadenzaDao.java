package com.finanziaria.dao;

import java.util.List;

import com.finanziaria.beans.Scadenza;

public interface ScadenzaDao {

    // metodo per elencare le Scadenze
    List<Scadenza> lister() throws DaoException;
}
