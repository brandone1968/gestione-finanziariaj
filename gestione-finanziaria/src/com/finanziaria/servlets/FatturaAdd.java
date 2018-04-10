package com.finanziaria.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finanziaria.beans.Fattura;
import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.DittaDao;
import com.finanziaria.dao.FatturaDao;
import com.finanziaria.forms.FatturaForm;

/**
 * Servlet implementation class Home
 */
@WebServlet( "/FatturaAdd" )
public class FatturaAdd extends HttpServlet {
    private static final long   serialVersionUID     = 1L;

    public static final String  ATT_USER             = "fattura";
    public static final String  ATT_FORM             = "form";
    public static final String  VISTA_ADD            = "/WEB-INF/fatturaAdd.jsp";
    public static final String  VISTA_DETTAGLIO      = "/WEB-INF/fattura.jsp";
    public static final Integer DITTA_DEFAULT_EMETTE = 1;
    public static final Integer DITTA_DEFAULT_RICEVE = 2;
    private DittaDao            dittaDao;
    private FatturaDao          fatturaDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.dittaDao = daoFactory.getDittaDao();
        this.fatturaDao = daoFactory.getFatturaDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.setAttribute( "active_page", "fatture" );
        request.setAttribute( "ditte", dittaDao.lista() );
        Integer idDitta1 = dittaDao.findDittaDefault( DITTA_DEFAULT_EMETTE );
        Integer idDitta2 = dittaDao.findDittaDefault( DITTA_DEFAULT_RICEVE );
        request.setAttribute( "default_emissione", idDitta1 );
        request.setAttribute( "default_ricevente", idDitta2 );

        request.setAttribute( "ditta1", dittaDao.findDatiDitta( idDitta1 ) );
        request.setAttribute( "ditta2", dittaDao.findDatiDitta( idDitta2 ) );

        request.setAttribute( "fattura", fatturaDao.impostaFatturaDefault() );
        this.getServletContext().getRequestDispatcher( VISTA_ADD ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        request.setAttribute( "active_page", "fatture" );
        request.setAttribute( "ditte", dittaDao.lista() );
        request.setAttribute( "default_emissione", request.getParameter( "ditta1" ) );
        request.setAttribute( "default_ricevente", request.getParameter( "ditta2" ) );
        request.setAttribute( "ditta1",
                dittaDao.findDatiDitta( Integer.parseInt( request.getParameter( "ditta1" ) ) ) );
        request.setAttribute( "ditta2",
                dittaDao.findDatiDitta( Integer.parseInt( request.getParameter( "ditta2" ) ) ) );

        /* Preparazione oggetto form */
        FatturaForm form = new FatturaForm();

        /*
         * Chiamata al'elaborazione e alla validazione della richiesta e al
         * recupero del bean risultante
         */
        Fattura fattura = form.aggiungeFattura( request );

        /* Memorizzazione del form e del bean nell'oggetti request */
        String risultato = form.getRisultato();
        String vista = "";
        if ( risultato.equals( "Errore nell'inserimento." ) ) {
            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, fattura );
            vista = VISTA_ADD;
        } else {
            request.setAttribute( "fattura", fatturaDao.find( 2 ) );
            vista = VISTA_DETTAGLIO;
        }

        /*
         * Trasmissione della coppia di oggetti request/response alla pagina JSP
         */
        this.getServletContext().getRequestDispatcher( vista ).forward( request, response );
    }

}
