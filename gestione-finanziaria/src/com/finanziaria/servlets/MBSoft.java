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
@WebServlet( "/MBSoft" )
public class MBSoft extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String VISTA            = "/WEB-INF/mbsoft.jsp";
    // private String active_page = "mbsoft";

    public MBSoft() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        request.setAttribute( "active_page", "mbsoft" );
        this.getServletContext().getRequestDispatcher( VISTA ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
