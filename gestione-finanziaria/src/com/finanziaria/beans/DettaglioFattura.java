package com.finanziaria.beans;

import java.sql.Timestamp;

public class DettaglioFattura {
    private Integer   id;
    private String    descrizione;
    private Integer   qta;
    private Integer   unitaMisuraQta;
    private Float     tariffa;
    private Timestamp dettaglioFatturaTimeStamp;

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

    public Integer getQta() {
        return qta;
    }

    public void setQta( Integer qta ) {
        this.qta = qta;
    }

    public Integer getUnitaMisuraQta() {
        return unitaMisuraQta;
    }

    public void setUnitaMisuraQta( Integer unitaMisuraQta ) {
        this.unitaMisuraQta = unitaMisuraQta;
    }

    public Float getTariffa() {
        return tariffa;
    }

    public void setTariffa( Float tariffa ) {
        this.tariffa = tariffa;
    }

    public Timestamp getDettaglioFatturaTimeStamp() {
        return dettaglioFatturaTimeStamp;
    }

    public void setDettaglioFatturaTimeStamp( Timestamp dettaglioFatturaTimeStamp ) {
        this.dettaglioFatturaTimeStamp = dettaglioFatturaTimeStamp;
    }

}
