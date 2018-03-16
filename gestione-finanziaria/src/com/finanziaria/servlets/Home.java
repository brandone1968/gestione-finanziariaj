package com.finanziaria.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finanziaria.dao.DaoException;
import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.ScadenzaDao;

/**
 * Servlet implementation class Home
 */
@WebServlet( "/Home" )
public class Home extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String VISTA            = "/WEB-INF/home.jsp";

    private ScadenzaDao        scadenzaDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.scadenzaDao = daoFactory.getScadenzaDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            request.setAttribute( "active_page", "home" );
            request.setAttribute( "scadenze", scadenzaDao.lister() );
        } catch ( DaoException e ) {
            request.setAttribute( "erreur", e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
