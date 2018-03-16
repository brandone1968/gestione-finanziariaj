package com.finanziaria.beans;

import java.time.LocalDate;

public class Scadenza {

    private Integer   id;
    private String    descrizione;
    private LocalDate dataScadenza;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza( LocalDate dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

}
