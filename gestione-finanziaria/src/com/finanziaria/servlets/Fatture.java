package com.finanziaria.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.FatturaDao;

/**
 * Servlet implementation class Home
 */
@WebServlet( "/Fatture" )
public class Fatture extends HttpServlet {
    private static final long  serialVersionUID  = 1L;

    public static final String VISTA             = "/WEB-INF/fatture.jsp";
    private FatturaDao         fatturaDao;
    private String             tipoPagamento     = "0";
    private String             anno;
    private List<String>       anniSelezionabili = new ArrayList<String>();

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.fatturaDao = daoFactory.getFatturaDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        request.setAttribute( "active_page", "fatture" );
        request.setAttribute( "tipoPagamento", tipoPagamento );

        if ( tipoPagamento.equals( "0" ) ) {
            anniSelezionabili = fatturaDao.findAllYearBalance();
        } else {
            anniSelezionabili = fatturaDao.findAllYearIssue();
        }
        anno = anniSelezionabili.get( 0 );
        request.setAttribute( "anno", anno );
        request.setAttribute( "fatture", fatturaDao.lister( tipoPagamento, anno ) );

        request.setAttribute( "anniSelezionabili", anniSelezionabili );

        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            request.setAttribute( "active_page", "fatture" );
            tipoPagamento = request.getParameter( "tipoPagamento" );
            anno = request.getParameter( "anno" );

            if ( tipoPagamento.equals( "0" ) ) {
                anniSelezionabili = fatturaDao.findAllYearBalance();
            } else {
                anniSelezionabili = fatturaDao.findAllYearIssue();
            }

            request.setAttribute( "tipoPagamento", tipoPagamento );
            request.setAttribute( "anno", anno );
            request.setAttribute( "anniSelezionabili", anniSelezionabili );
            request.setAttribute( "fatture", fatturaDao.lister( tipoPagamento, anno ) );
        } catch ( Exception e ) {
            request.setAttribute( "erreur", e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

}
