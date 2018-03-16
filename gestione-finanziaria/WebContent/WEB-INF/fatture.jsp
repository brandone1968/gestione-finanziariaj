<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Gestione Finanziaria - Fatture</title>
	<%@ include file="intestazione.jsp" %>
</head>
<body>
	<!-- Inserisco il menu -->
	<div class="container">
		<%@ include file="layout.jsp" %>	
	
		<div id="content">
			<p class="spazio" ></p>
		    <form name="ricercaFattura" action="fatture" method="post"> 
		        <div class="row">
		            <div class="col-xs-2">
		                <label class="control-label" for="tipoPagamento">Fatture trovate <span class="badge">${fn:length(fatture)}</span></label>
		            </div>
		            <div class="col-xs-2">
		                <select class="form-control" name = "tipoPagamento" id = "tipoPagamento" placeholder=".col-xs-2" onchange='document.ricercaFattura.submit()'>
							<c:choose>
							  <c:when test="${tipoPagamento.equals('0')}">
							    <option value="0" selected>Emesse</option>
								<option value="1">Incassate</option>
							  </c:when>
							  <c:otherwise>
							    <option value="0">Emesse</option> 
								<option value="1" selected>Incassate</option> 
							  </c:otherwise>
							</c:choose>
		                </select>            
		            </div>
		            <div class="col-xs-1">
		                <label class="control-label" for="anno">Nel</label>
		            </div>
		            <div class="col-xs-2">	
		
		                <select class="form-control" name = "anno" id = "anno" placeholder=".col-xs-2"  onchange='document.ricercaFattura.submit()'>
		                    <c:forEach var="annoSelezionabile" items="${ anniSelezionabili }">	
			                    <c:choose>
									<c:when test="${annoSelezionabile eq anno}">						
										<option value="${annoSelezionabile}" selected>${annoSelezionabile}</option>  
								  	</c:when>
								  	<c:otherwise>
										<option value="${annoSelezionabile}">${annoSelezionabile}</option>  
								  	</c:otherwise>
								</c:choose>
		                   	</c:forEach>
		                </select>
		            </div>
		        </div>
		    </form>
		    <br>
		    
		        <table id="tableData" class="table table-bordered table-hover">
        <thead>
            <tr>
                <th class="text-center">Num.</th>
                <th class="text-center">Descrizione</th>
                <th class="text-center">Emessa da</th>
                <th class="text-center">A favore di</th>
                <th class="text-center">Importo <i class="fa fa-eur" aria-hidden="true"></i></th>
                <th class="text-center">Data emissione</th>
                <th class="text-center">Data pagamento</th>
                <th id="nuovaFattura" colspan="3" class="text-center"><a href="<c:url value="/fatturaAdd"><c:param name = "idFattura" value ="${ Null }" /></c:url>"><i class="fa fa-plus-circle fa-lg" data-toggle="tooltip" title="Nuova fattura" aria-hidden="true"></i></a></th>
            </tr>
        </thead>
        <tbody id="myTable">

		<c:forEach var="fattura" items="${ fatture }">
            <fattura>
                <tr>
                    <td class="text-center"><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.numFattura }" /></a></td>
                    <td><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.descrizione }" /></a></td>
                    <td class="text-left" ><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.ditta1.denominazione }" /></a></td>
                    <td class="text-left"><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.ditta2.denominazione }" /></a></td>
                    <td class="text-left"><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.totFattura }" /></a></td>
                    <td class="text-center"><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.dataFattura }" /></a></td>

					<c:choose>
						<c:when test="${fattura.dataPagamento == null}">
	                        <td class="text-center"><a href="#"><i class="fa fa-exclamation-triangle fa-lg" data-toggle="tooltip" title="Inserire data incasso" aria-hidden="true"></i></a></td>
	                        <td class="text-center"><a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true" data-toggle="tooltip" title="Modifica"></i></a></td>
	                        <td class="text-center"><a href="#"><i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" title="Cancella"></i></a></td>
	                        <td class="text-center"><a href="#"><i class="fa fa-file-pdf-o" aria-hidden="true" data-toggle="tooltip" title="Visualizza"></i></a></td>
						</c:when>
						<c:otherwise>
                        	<td class="text-center"><a title="Dettagli fattura" href="<c:url value="/fattura"><c:param name = "idFattura" value ="${ fattura.id }" /></c:url>"><c:out value="${ fattura.dataPagamento }" /></a></td>
                        	<td colspan="3" class="text-center"><a href="#"><i class="fa fa-file-pdf-o" aria-hidden="true" data-toggle="tooltip" title="Visualizza"></i></a></td>
						</c:otherwise>
					</c:choose>
                </tr>
            </fattura>
		</c:forEach> 


    </tbody>
</table>
		    
		    
		</div> 
		
	</div> 
	<!-- Inserisco il footer -->
	<%@ include file="footer.jsp" %>
    </body>
</html>