<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Gestione Finanziaria - Fattura</title>
	<%@ include file="intestazione.jsp" %>
</head>
<body>
	<!-- Inserisco il menu -->
	<div class="container">
		<%@ include file="layout.jsp" %>	
	

	<p>
    <br>
    <h3>Dettagli fattura</h3>
    <br>

    <table id="tableDettaglioFattura" class="table table-bordered table-hover">
        <tr>
            <td><strong><c:out value="${ fattura.ditta1.denominazione }" /></strong></td><td><strong><c:out value="${ fattura.ditta2.denominazione }" /></strong></td>
        </tr><tr>

            <td><c:out value="${ fattura.ditta1.indirizzo }" /></td><td><c:out value="${ fattura.ditta2.indirizzo }" /></td>
        </tr><tr>

            <td><c:out value="${ fattura.ditta1.cap }" /> - <c:out value="${ fattura.ditta1.citta }" /></td><td><c:out value="${ fattura.ditta2.cap }" /> - <c:out value="${ fattura.ditta2.citta }" /></td>
        </tr><tr>
            <td>C.F. <c:out value="${ fattura.ditta1.cf }" /></td><td>C.F. <c:out value="${ fattura.ditta2.cf }" /></td>
        </tr><tr>
            <td>P.IVA <c:out value="${ fattura.ditta1.piva }" /></td><td>P.IVA <c:out value="${ fattura.ditta2.piva }" /></td>
        </tr>
    </table>
    <br>
    <table id="tableFattura" class="table table-bordered table-hover">
        <tr>
            <td colspan="2">Fattura N° <strong><c:out value="${ fattura.numFattura }" /></strong> del <strong><c:out value="${ fattura.dataFattura }" /></strong></td>
        </tr>
    </table>
    <br>
    <table id="tableFattura" class="table table-bordered table-hover">
        <thead>
            <tr>
                <th class="text-center">Descrizione</th>
                <th class="text-center">Quantità</th>
                <th class="text-center">Importo</th>
                <th class="text-center">Totale</th>
             </tr>
        </thead>
        
        <c:forEach var="dettaglioFattura" items="${ fattura.dettagliFattura }">
            <tr>
                <td><c:out value="${ dettaglioFattura.descrizione }" /></td>
                <c:choose>
						<c:when test="${dettaglioFattura.unitaMisuraQta == 0}">
                	        <td><c:out value="${ dettaglioFattura.qta }" /> gg</td>
                    		<td><c:out value="${ dettaglioFattura.tariffa }" /></td>
                    		<td><c:out value="${ dettaglioFattura.tariffa * dettaglioFattura.qta }" /></td>
                
                		</c:when>
				<c:otherwise>
                    <td><c:out value="${ dettaglioFattura.qta }" /> ore</td>
                    <td><c:out value="${ dettaglioFattura.tariffa }" /></td>
                    <td><c:out value="${ dettaglioFattura.tariffa / 8 * dettaglioFattura.qta }" /></td>
               </c:otherwise>
					</c:choose>
            </tr>
<!--             <td>No comments yet.</td> -->
        </c:forEach>
        
        <tr>
            <td colspan="3" class="text-right">Imponibile</td><td><c:out value="${ fattura.imponibile }" /></td>
            </tr><tr>
            <td colspan="3" class="text-right">IVA <c:out value="${ fattura.percentualeIVA }" />%</td><td><c:out value="${ fattura.iva }" /></td>
            </tr><tr>
            <td colspan="3" class="text-right">Totale fattura</td><td><c:out value="${ fattura.totFattura }" /></td>
        </tr>

    </table>
        
    <br>
    <table id="tableNoteFattura" class="table table-bordered">
        <tr>
            <td colspan="2"><strong>Note: </strong><c:out value="${ fattura.noteFattura }" /></td>
        </tr>
    </table>
</p>
	
	
		
	</div> 
	<!-- Inserisco il footer -->
	<%@ include file="footer.jsp" %>
    </body>
</html>