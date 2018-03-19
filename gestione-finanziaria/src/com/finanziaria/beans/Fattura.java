package com.finanziaria.beans;

import java.time.LocalDate;
import java.util.List;

public class Fattura {

    private Integer                id;
    private Integer                numFattura;
    private String                 descrizione;
    private LocalDate              dataFattura;
    private LocalDate              dataPagamento;
    private Float                  imponibile;
    private Float                  percentualeIVA;
    private Float                  iva;
    private Float                  totFattura;
    private Ditta                  ditta1;
    private Ditta                  ditta2;
    private String                 noteFattura;
    private List<DettaglioFattura> dettagliFattura;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getNumFattura() {
        return numFattura;
    }

    public void setNumFattura( Integer numFattura ) {
        this.numFattura = numFattura;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataFattura() {
        return dataFattura;
    }

    public void setDataFattura( LocalDate dataFattura ) {
        this.dataFattura = dataFattura;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento( LocalDate dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public Float getImponibile() {
        return imponibile;
    }

    public void setImponibile( Float imponibile ) {
        this.imponibile = imponibile;
    }

    public Float getPercentualeIVA() {
        return percentualeIVA;
    }

    public void setPercentualeIVA( Float percentualeIVA ) {
        this.percentualeIVA = percentualeIVA;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva( Float iva ) {
        this.iva = iva;
    }

    public Float getTotFattura() {
        return totFattura;
    }

    public void setTotFattura( Float totFattura ) {
        this.totFattura = totFattura;
    }

    public Ditta getDitta1() {
        return ditta1;
    }

    public void setDitta1( Ditta ditta1 ) {
        this.ditta1 = ditta1;
    }

    public Ditta getDitta2() {
        return ditta2;
    }

    public void setDitta2( Ditta ditta2 ) {
        this.ditta2 = ditta2;
    }

    public String getNoteFattura() {
        return noteFattura;
    }

    public void setNoteFattura( String noteFattura ) {
        this.noteFattura = noteFattura;
    }

    public List<DettaglioFattura> getDettagliFattura() {
        return dettagliFattura;
    }

    public void setDettagliFattura( List<DettaglioFattura> dettagliFattura ) {
        this.dettagliFattura = dettagliFattura;
    }

}
