<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% response.setContentType("text/html");
   String idDitta2 = request.getParameter("idDitta");


%>
${ditta.denominazione} </br> ${ditta.indirizzo} </br> ${ditta.cap} ${ditta.citta} </br> ${ditta.cf} </br> ${ditta.piva}
