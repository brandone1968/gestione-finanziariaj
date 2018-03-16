package com.finanziaria.beans;

import java.sql.Timestamp;

public class Ditta {

    private Integer   id_ditta;
    private String    denominazione;
    private String    indirizzo;
    private Integer   cap;
    private String    citta;
    private String    cf;
    private String    piva;
    private Boolean   default_immissione;
    private Timestamp dittaTimeStamp;

    public Integer getId_ditta() {
        return id_ditta;
    }

    public void setId_ditta( Integer id_ditta ) {
        this.id_ditta = id_ditta;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap( Integer cap ) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta( String citta ) {
        this.citta = citta;
    }

    public String getCf() {
        return cf;
    }

    public void setCf( String cf ) {
        this.cf = cf;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva( String piva ) {
        this.piva = piva;
    }

    public Boolean getDefault_immissione() {
        return default_immissione;
    }

    public void setDefault_immissione( Boolean default_immissione ) {
        this.default_immissione = default_immissione;
    }

    public Timestamp getDittaTimeStamp() {
        return dittaTimeStamp;
    }

    public void setDittaTimeStamp( Timestamp dittaTimeStamp ) {
        this.dittaTimeStamp = dittaTimeStamp;
    }

}
