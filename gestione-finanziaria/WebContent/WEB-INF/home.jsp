<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Gestione Finanziaria - Home</title>
	<%@ include file="intestazione.jsp" %>
</head>
<body>
	<!-- Inserisco il menu -->
	<div class="container">
		<%@ include file="layout.jsp" %>	
	
		<div id="content">
	    	<p class="spazio">Scadenze del mese</p> 
	
	    	<table class="table table-bordered table-hover">
		        <thead>
		            <tr>
		                <th class="text-center">Descrizione scadenza</th>
		                <th class="text-center">Data scadenza</th>
		            </tr>
		        </thead>
		        <tbody>  
		        	    
		                <c:forEach var="scadenza" items="${ scadenze }">
		                	<scadenza>
		                		<tr>
            						<td><c:out value="${ scadenza.descrizione }" /></td>
            						<td>
            						<!-- 
            							parseDate per eseguire la conversione in stringa 
            							e quindi eseguire la formattazione. 
            						 -->
            						<fmt:parseDate value="${scadenza.dataScadenza}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
									<fmt:formatDate value="${parsedDate}" var="stdDatum" type="date" pattern="dd MMMM" />
									<c:out value="${ stdDatum }" />
									</td>
            					</tr>
            				</scadenza> 
        				</c:forEach> 
        			         					       
	    		</tbody>
			</table>
		</div> 
	</div> 
	<!-- Inserisco il footer -->
	<%@ include file="footer.jsp" %>
    </body>
</html>