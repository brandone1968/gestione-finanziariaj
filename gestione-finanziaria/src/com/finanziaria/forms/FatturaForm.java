package com.finanziaria.forms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.finanziaria.beans.DettaglioFattura;
import com.finanziaria.beans.Fattura;
import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.FatturaDao;

public class FatturaForm {
    private static final String CAMPO_NUM_FATTURA           = "numFattura";
    private static final String CAMPO_DESCRIZIONE           = "descrizioneFattura";
    private static final String CAMPO_DATA_FATTURA          = "dataFattura";
    private static final String CAMPO_DATA_PAGAMENTO        = "dataPagamento";
    private static final String CAMPO_DITTA1                = "ditta1";
    private static final String CAMPO_DITTA2                = "ditta2";
    private static final String CAMPO_DESCRIZIONE_DETTAGLIO = "descrizioneDettaglio_";
    private static final String CAMPO_QTA                   = "qta_";
    private static final String CAMPO_UNITA_MISURA_QTA      = "unitaMisuraQta_";
    private static final String CAMPO_TARIFFA               = "importo_";
    private static final String CAMPO_NUM_DETTAGLI          = "numDettagli";
    // Imposto formattazione data giorno/mese/anno
    private static final String FORMATO_DATA                = "dd/MM/yyyy";

    private String              risultato;
    private Map<String, String> errore                      = new HashMap<String, String>();
    private FatturaDao          fatturaDao;

    private String              descrizioneFatturaProva     = "";

    public String getRisultato() {
        return risultato;
    }

    public Map<String, String> getErrore() {
        return errore;
    }

    public Fattura aggiungeFattura( HttpServletRequest request ) {
        String numFattura = getValoreCampo( request, CAMPO_NUM_FATTURA );
        String descrizione = getValoreCampo( request, CAMPO_DESCRIZIONE );
        String dataFattura = getValoreCampo( request, CAMPO_DATA_FATTURA );
        String dataPagamento = getValoreCampo( request, CAMPO_DATA_PAGAMENTO );
        String ditta1 = getValoreCampo( request, CAMPO_DITTA1 );
        String ditta2 = getValoreCampo( request, CAMPO_DITTA2 );
        Integer numDettagli = Integer.parseInt( getValoreCampo( request, CAMPO_NUM_DETTAGLI ) );

        Fattura fattura = new Fattura();

        List<DettaglioFattura> dettagliFattura = new ArrayList<DettaglioFattura>();

        String descrizioneDettaglio = "";
        String qta = "";
        String unitaMisuraQta = "";
        String importo = "";

        // ciclo per controllare tutti i campi di dettaglio e ricomporre l'array
        // di oggetti dettaglio
        for ( int j = 1; j <= numDettagli; j++ ) {
            descrizioneDettaglio = getValoreCampo( request, CAMPO_DESCRIZIONE_DETTAGLIO + j );

            qta = getValoreCampo( request, CAMPO_QTA + j );
            unitaMisuraQta = getValoreCampo( request, CAMPO_UNITA_MISURA_QTA + j );
            importo = getValoreCampo( request, CAMPO_TARIFFA + j );
            importo = importo.replace( ',', '.' );

            DettaglioFattura dettaglioFattura = new DettaglioFattura();
            dettaglioFattura.setId( j );
            dettaglioFattura.setDescrizione( descrizioneDettaglio );

            try {
                validationQta( qta );
                dettaglioFattura.setQta( Integer.parseInt( qta ) );
            } catch ( Exception e ) {
                setErrore( CAMPO_QTA + j, e.getMessage() );
            }

            dettaglioFattura.setUnitaMisuraQta( Integer.parseInt( unitaMisuraQta ) );

            try {
                validationTariffa( importo );
                dettaglioFattura.setTariffa( Float.parseFloat( importo ) );
            } catch ( Exception e ) {
                setErrore( CAMPO_TARIFFA + j, e.getMessage() );
            }

            dettagliFattura.add( dettaglioFattura );
        }

        fattura.setDettagliFattura( dettagliFattura );

        try {
            validationNumFattura( numFattura );
        } catch ( Exception e ) {
            setErrore( CAMPO_NUM_FATTURA, e.getMessage() );
        }

        if ( ( numFattura != null ) && ( numFattura != "" ) ) {
            fattura.setNumFattura( Integer.parseInt( numFattura ) );
        }

        try {
            validationDescrizione( descrizione );
        } catch ( Exception e ) {
            setErrore( CAMPO_DESCRIZIONE, e.getMessage() );
        }
        fattura.setDescrizione( descrizione );

        try {
            validationDataFattura( dataFattura );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMATO_DATA );
            LocalDate data = LocalDate.parse( dataFattura, formatter );
            fattura.setDataFattura( data );
        } catch ( Exception e ) {
            setErrore( CAMPO_DATA_FATTURA, e.getMessage() );
        }

        try {
            validationDataPagamento( dataPagamento, dataFattura );
        } catch ( Exception e ) {
            setErrore( CAMPO_DATA_PAGAMENTO, e.getMessage() );
        }

        // Se la data di pagamento è stata inserita deve essere visualizzata
        // L'eventuale errore è già stato segnalato dalle istruzioni precedenti
        if ( dataPagamento != null ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMATO_DATA );
            LocalDate data2 = LocalDate.parse( dataPagamento, formatter );
            fattura.setDataPagamento( data2 );
        }

        if ( errore.isEmpty() ) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            this.fatturaDao = daoFactory.getFatturaDao();

            Fattura fatturaProva = fatturaDao.find( 1 );
            descrizioneFatturaProva = fatturaProva.getDescrizione();
            risultato = "Fattura inserita." + descrizioneFatturaProva;
        } else {
            risultato = "Errore nell'inserimento.";
        }

        return fattura;
    }

    private void validationNumFattura( String numFattura ) throws Exception {
        if ( ( numFattura == null ) || ( numFattura == "" ) ) {
            throw new Exception( "Per cortesia, inserire un numero di fattura valido." );
        } else if ( Integer.parseInt( numFattura ) <= 0 ) {
            throw new Exception( "Per cortesia, inserire un numero di fattura valido." );
        }
    }

    private void validationDescrizione( String descrizione ) throws Exception {
        if ( descrizione == null ) {
            throw new Exception( "Per cortesia, inserire una descrizione." );
        }
    }

    private void validationDataFattura( String dataFattura ) throws Exception {
        if ( ( dataFattura == null ) || ( dataFattura.length() == 0 ) ) {
            throw new Exception( "Per cortesia, inserire la data dell'emissione della fattura." );
        } else if ( !testFormatoData( dataFattura ) ) {
            throw new Exception( "Per cortesia, inserire una data di emissione della fattura valida." );
        }
    }

    private void validationDataPagamento( String dataPagamento, String dataFattura ) throws Exception {
        if ( ( dataPagamento != null ) && ( dataFattura != null ) ) {
            if ( testFormatoData( dataPagamento ) ) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMATO_DATA );
                LocalDate data1 = LocalDate.parse( dataFattura, formatter );
                LocalDate data2 = LocalDate.parse( dataPagamento, formatter );
                if ( data1.isAfter( data2 ) ) {
                    throw new Exception(
                            "Attenzione La data del pagamento non può essere precedente a quella di emissione della fattura." );
                }
            } else {
                throw new Exception( "Per cortesia, inserire una data di pagamento valida." );
            }
        }
    }

    private void validationQta( String qta ) throws Exception {
        if ( !isNumeric( qta ) ) {
            throw new Exception( "Per cortesia, inserire una quantità valida" );
        }
    }

    private void validationTariffa( String importo ) throws Exception {
        if ( !isNumeric( importo ) ) {
            throw new Exception( "Per cortesia, inserire un importo valido" );
        }
    }

    /*
     * Aggiungo un messaggio corrispondente al campo specificato nella mappa
     * errori.
     */
    private void setErrore( String champ, String message ) {
        errore.put( champ, message );
    }

    /*
     * Metodo statico che restitutisce o il valore del campo o null se il campo
     * è vuoto
     */
    private static String getValoreCampo( HttpServletRequest request, String nomChamp ) {
        String valore = request.getParameter( nomChamp );
        if ( valore == null || valore.trim().length() == 0 ) {
            return null;
        } else {
            return valore.trim();
        }
    }

    /*
     * Metodo che verifica che una data sia valida per il formato data
     */
    private static Boolean testFormatoData( String data ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMATO_DATA );
            LocalDate.parse( data, formatter );
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }

    /*
     * Metodo che verifica che una stringa sia un numero
     */
    public static boolean isNumeric( String inputData ) {
        return inputData.matches( "[+]?\\d+(\\.\\d+)?" );
    }
}
