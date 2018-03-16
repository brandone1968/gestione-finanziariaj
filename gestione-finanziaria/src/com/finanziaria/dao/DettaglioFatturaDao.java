package com.finanziaria.dao;

import java.util.List;

import com.finanziaria.beans.DettaglioFattura;

public interface DettaglioFatturaDao {

    // metodo per elencare le Scadenze
    List<DettaglioFattura> findAllByFattura( Integer idFattura ) throws DaoException;
}
