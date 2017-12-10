<%@page import="br.com.consultamedica.modelo.implemento.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incluir Paciente</title>
</head>
<body>

	<form action="../usuario" method="post">

		<fieldset>
			
			<legend>Cadastre-se</legend>
			
			<label>Nome: <input type="text" name="<%=Usuario.NM_ATR_Nome%>"required/></label><br/><br/>
			<label>E-mail: <input type="text" name="<%=Usuario.NM_ATR_Email%>"required/></label><br/><br/>
			<label>Senha: <input type="password" name="<%=Usuario.NM_ATR_Senha%>" maxlength="8" required/></label><br/><br/>
			<label>Cartão do SUS: <input type="text" name="<%=Usuario.NM_ATR_cartaoSus%>" maxlength="15" required/></label><br/><br/>
			
			
			<button onclick="form.action: '../usuario';" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Cadastrar</button>
			
		</fieldset>
	
	</form>

</body>
</html>
