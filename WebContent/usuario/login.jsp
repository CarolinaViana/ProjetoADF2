<%@page import="br.com.consultamedica.servlet.LoginS"%>
<%@page import= "br.com.consultamedica.modelo.implemento.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	
	String mensagem = (request.getAttribute(LoginS.NM_MENSAGEM) != null) ? (String) request.getAttribute(LoginS.NM_MENSAGEM): "";

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="../login" method="post">
	
		<label>E-mail: <input type="text" name="<%=Usuario.NM_ATR_Email%>" /></label>
		<label>Senha: <input type="password" name="<%=Usuario.NM_ATR_Senha%>" /></label>
		
		
		<button onclick="form.action: '../login';">Login</button>
	
	</form>
	
	
	<label><strong style="color: red;"><%=mensagem%></strong></label>

</body>
</html>