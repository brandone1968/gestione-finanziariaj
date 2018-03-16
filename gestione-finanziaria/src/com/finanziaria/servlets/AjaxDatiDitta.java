package com.finanziaria.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finanziaria.dao.DaoFactory;
import com.finanziaria.dao.DittaDao;

/**
 * Servlet implementation class AjaxDatiDitta
 */
@WebServlet( "/AjaxDatiDitta" )
public class AjaxDatiDitta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DittaDao          dittaDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.dittaDao = daoFactory.getDittaDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append( "Served at: " ).append( request.getContextPath() );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // prendo valore selezionato nella tendina
        String idDitta = request.getParameter( "idDitta" );
        request.setAttribute( "descrizione", "mia ditta" );
        request.setAttribute( "idDitta", idDitta );
        request.setAttribute( "ditta", dittaDao.findDatiDitta( Integer.parseInt( idDitta ) ) );

        // response.setContentType( "text/html" );
        // response.getWriter().write( "Hello World!" + idDitta );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/ajaxDatiDitta.jsp" ).forward( request, response );

    }

}
