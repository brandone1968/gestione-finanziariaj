package com.finanziaria.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.DettaglioFatturaDao;
import com.finanziaria.dao.FatturaDao;

/**
 * Servlet implementation class Home
 */
@WebServlet( "/Fattura" )
public class Fattura extends HttpServlet {
    private static final long   serialVersionUID = 1L;

    public static final String  VISTA            = "/WEB-INF/fattura.jsp";
    private FatturaDao          fatturaDao;
    private DettaglioFatturaDao dettaglioFatturaDao;
    private Integer             idFattura;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fatturaDao = daoFactory.getFatturaDao();
        this.dettaglioFatturaDao = daoFactory.getDettaglioFatturaDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        idFattura = Integer.parseInt( request.getParameter( "idFattura" ) );
        request.setAttribute( "active_page", "fatture" );
        request.setAttribute( "fattura", fatturaDao.find( idFattura ) );
        request.setAttribute( "dettagli", dettaglioFatturaDao.findAllByFattura( idFattura ) );

        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            request.setAttribute( "active_page", "fatture" );

        } catch ( Exception e ) {
            request.setAttribute( "erreur", e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

}
