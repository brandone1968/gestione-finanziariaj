package com.finanziaria.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet( "/Statistiche" )
public class Statistiche extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String VISTA            = "/WEB-INF/statistiche.jsp";

    public Statistiche() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.setAttribute( "active_page", "statistiche" );
        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
