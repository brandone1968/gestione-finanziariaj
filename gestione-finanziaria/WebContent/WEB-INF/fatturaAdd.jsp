<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
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
	
		<p>
    <br>
    <h3>Nuova fattura</h3>
    <br>
<form method="post" action="fatturaAdd">
    <table id="tableDettaglioFattura" class="table table-bordered table-hover">
        <tr>
            <td>    
				<!--  Ditta che emette la fattura -->  
				<select class="form-control" name = "ditta1" id = "ditta1" placeholder=".col-xs-2" >
		        	<c:forEach var="ditta1" items="${ ditte }">	
			        	<c:choose>
							<c:when test="${default_emissione eq ditta1.id_ditta}">						
								<option value="${ditta1.id_ditta}" selected>${ditta1.denominazione}</option>  
						 	</c:when>
							<c:otherwise>
								<option value="${ditta1.id_ditta}">${ditta1.denominazione}</option>  
						  	</c:otherwise>
						</c:choose>
		            </c:forEach>
		       </select>
            </td>
            <td>
				<!--  Ditta che riceve la fattura --> 
				<select class="form-control" name = "ditta2" id = "ditta2" placeholder=".col-xs-2"  onchange='document.ricercaFattura.submit()'>
		        	<c:forEach var="ditta2" items="${ ditte }">	
			        	<c:choose>
							<c:when test="${default_ricevente eq ditta2.id_ditta}">						
								<option value="${ditta2.id_ditta}" selected>${ditta2.denominazione}</option>  
						 	</c:when>
							<c:otherwise>
								<option value="${ditta2.id_ditta}">${ditta2.denominazione}</option>  
						  	</c:otherwise>
						</c:choose>
		            </c:forEach>
		       </select>
            </td>
        </tr>
    </table>
    <br>
    
<!--     Visualizzo descrizioni e dati delle ditte selezionate -->
    <table id="tableFattura" class="table table-bordered table-hover">
    	<div> 
    		<tr>
    			<td id="descrizioneDitta1" width="50%">${ditta1.denominazione} </br> ${ditta1.indirizzo} </br> ${ditta1.cap} ${ditta1.citta} </br> ${ditta1.cf} </br> ${ditta1.piva}</td>
    			<td id="descrizioneDitta2" width="50%">${ditta2.denominazione} </br> ${ditta2.indirizzo} </br> ${ditta2.cap} ${ditta2.citta} </br> ${ditta2.cf} </br> ${ditta2.piva}</td>
    		</tr>  	
        </div>
    </table> 
        
    <table id="tableFattura" class="table table-bordered table-hover">
        <tr>
            <td>
				<label for="numFattura">Numero fattura <span class="obbligatorio">*</span></label>
				<input type="text" id="numFattura" name="numFattura"
				value="<c:out value="${fattura.numFattura}"/>" size="25"
				maxlength="25" /> <br><span class="errore">${form.errore['numFattura']}</span>
            </td>

            <td>        
            	<!-- inserisco datapicker -->

             	<fmt:parseDate pattern="yyyy-MM-dd" value="${fattura.dataFattura}" var="dataFattura" />
				     
             	<div class="input-group date noAcapo" id="date1" name="date1">      
             		<span class="label-group-addon-mb">Data emissione <span class="obbligatorio">*</span></span>    
                    <input type="text" class="form-control" id="dataFattura" name="dataFattura"
                    value="<fmt:formatDate value="${dataFattura}" pattern="dd/MM/yyyy" />" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

                <br><span class="errore">${form.errore['dataFattura']}</span>
			</td>
			<td>
				<fmt:parseDate pattern="yyyy-MM-dd" value="${fattura.dataPagamento}" var="dataPagamento" />
             	                	
                <div class="input-group date noAcapo" id="date2" name="date2">  
                <span class="label-group-addon-mb">Data pagamento </span>
                    <input type="text" class="form-control" id="dataPagamento" name="dataPagamento" value="<fmt:formatDate value="${dataPagamento}" pattern="dd/MM/yyyy" />" /> 
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <br><span class="errore">${form.errore['dataPagamento']}</span>
             </td>
	<!--  fine inserimento -->

        </tr>
        		<tr>
            <td colspan="3" >
				<label for="descrizioneFattura">Descrizione fattura <span class="obbligatorio">*</span></label>
				<input type="text" id="descrizioneFattura" name="descrizioneFattura"
				value="<c:out value="${fattura.descrizione}"/>" size="130"
				maxlength="130" /> <br><span class="errore">${form.errore['descrizioneFattura']}</span>
			</td>
		</tr>

    </table>
	
	
		<fieldset>


			<br /> 
			<br /> <input type="submit" value="Salva fattura" class="sansLabel" />
			<br />

			<p class="${empty form.errore ? 'successo' : 'errore'}">${form.risultato}</p>
		</fieldset>
	</form>
		
		
	</div> 
	<!-- Inserisco il footer -->
	<%@ include file="footer.jsp" %>
    </body>
</html>